call heroku login
cd .\db-service
call heroku git:remote -a insapp-db-service
call git add . 
call git commit -am "deploy" 
call git push heroku master 

cd ..\customer-service
call heroku git:remote -a insapp-customer-service
call git add . 
call git commit -am "deploy" 
call git push heroku master 

cd ..\policy-service
call heroku git:remote -a insapp-policy-service
call git add . 
call git commit -am "deploy" 
call git push heroku master 

cd ..\user-service
call heroku git:remote -a insapp-user-service
call git add . 
call git commit -am "deploy" 
call git push heroku master 


cd ..\claim-service
call heroku logout
echo "przeloguj na insuranceapplicationsoft@gmail.com"
pause
call heroku login
call heroku git:remote -a insapp-claim-service
call git add . 
call git commit -am "deploy" 
call git push heroku master 
PAUSE