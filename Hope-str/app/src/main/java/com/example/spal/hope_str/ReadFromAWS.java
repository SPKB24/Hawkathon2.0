/*
 * Copyright 2010-2013 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.example.spal.hope_str;

import android.os.StrictMode;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.iterable.S3Objects;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * This sample demonstrates how to read some senior files
 */
public class ReadFromAWS {
    public static final String my_bucket = Constants.BUCKET_NAME;

    /*
     * Test
     */
    public static List<UserProfile> main(String query) throws Exception {
        /*
         * TODO
         * Create your credentials file at ~/.aws/credentials (C:\Users\USER_NAME\.aws\credentials for Windows users) 
         * and save the following lines after replacing the underlined values with your own.
         *
         * [default]
         * aws_access_key_id = YOUR_ACCESS_KEY_ID
         * aws_secret_access_key = YOUR_SECRET_ACCESS_KEY
         */

        List<UserProfile> profileList = null;

        try {
        	/*
        	 * Andy's sample senior reading code
        	 */
            profileList = pullProfiles(query);

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }

        return profileList;
    }

    /*
     * Reads all of the users files from my_bucket
     */
    public static List<UserProfile> pullProfiles(String query) throws Exception {
    	
    	ArrayList<UserProfile> users = new ArrayList<>();
    	
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        AWSCredentials credentials = new AWSCredentials() {
            @Override
            public String getAWSAccessKeyId() {
                return Constants.AWSAccessKeyId;
            }

            @Override
            public String getAWSSecretKey() {
                return Constants.AWSSecretKey;
            }
        };

        AmazonS3Client s3 = new AmazonS3Client(credentials);

        //loop through objects in SeniorProfiles/
        for( S3ObjectSummary summary : S3Objects.withPrefix(s3, my_bucket, query.toLowerCase()) ) {

            //creates a new request for each object
            GetObjectRequest Request = new GetObjectRequest( summary.getBucketName(), summary.getKey());

            //get the object from the database
            S3Object object = s3.getObject(Request);

            //Read the object's data into an InputStream
            InputStream is = object.getObjectContent();

            //Load Senior data from xml
            //TODO Replace with your xml processing function
            UserProfile temp = XmlParser.XMLToProfile(is);
            if (temp != null) {
                users.add(temp);
            }
        }

        return users;
    }

    /*
     * Reads all of the users files from my_bucket
     */
    public static UserProfile getPersonalProfile() throws Exception {

        ArrayList<UserProfile> users = new ArrayList<>();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        AWSCredentials credentials = new AWSCredentials() {
            @Override
            public String getAWSAccessKeyId() {
                return Constants.AWSAccessKeyId;
            }

            @Override
            public String getAWSSecretKey() {
                return Constants.AWSSecretKey;
            }
        };

        AmazonS3Client s3 = new AmazonS3Client(credentials);

        //loop through objects in SeniorProfiles/
        for( S3ObjectSummary summary : S3Objects.withPrefix(s3, my_bucket, "personal/") ) {

            //creates a new request for each object
            GetObjectRequest Request = new GetObjectRequest( summary.getBucketName(), summary.getKey());

            //get the object from the database
            S3Object object = s3.getObject(Request);

            //Read the object's data into an InputStream
            InputStream is = object.getObjectContent();

            //Load Senior data from xml
            //TODO Replace with your xml processing function
            UserProfile temp = XmlParser.XMLToProfile(is);
            if (temp != null) {
                users.add(temp);
            }
        }

        return users.get(0);
    }


    /**
     * Imports the contents of the specified input stream as text.
     *
     * @param input
     *            The input stream to display as text.
     *
     * @throws IOException
     */
    private static List<String> importStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        List<String> lines = new ArrayList<>();

        while (true) {
            String line = reader.readLine();
            if (line == null) { break; }

            lines.add(line);
        }

        if (lines.size() > 0) {
            // At this point, we have a list of lines for each XML file in 'lines'
            return lines;
        }
        return null;
    }
}