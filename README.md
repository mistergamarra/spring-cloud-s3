### Getting started
 
This example shows you how to store and retrieve files from S3. In this example we are using **us-west-2** region so you should update this region into application.properties if you changed it.

Prior to start, please you create an account named  **s3-account** and add this permissions **AmazonS3FullAccess, AWSCloudFormationFullAccess** in your IAM section from your aws console.

### 1. Create a profile into AWS CLI using this command

    aws configure --profile s3-account
    
    AWS Access Key ID [None]: ENTER_YOUR_ACCESS_KEY
    AWS Secret Access Key [None]: ENTER_YOUR_SECRET_KEY
    Default region name [None]: ENTER_USER_REGION
    Default output format [None]: PRESS ENTER    


### 2. Delete the **spring-cloud-s3-stack ** stack if exists

    aws cloudformation delete-stack  --stack-name spring-cloud-s3-stack --profile sqs-account


### 3. Create **spring-cloud-s3-stack ** stack into aws

    aws cloudformation create-stack  --stack-name spring-cloud-s3-stack --template-body file://cloudformation/bucket.yml --profile s3-account
    
### 4. Start the Spring Boot application

    ./mvnw spring-boot:run
        
### 5. Send a POST request to http://localhost:8080/api/file/image using POSTMAN to save Image File into S3.

### 6. Send a GET request to http://localhost:8080/api/file/image/{image_name} using POSTMAN to retrieve Image File from S3.

### 7. enjoy it!
