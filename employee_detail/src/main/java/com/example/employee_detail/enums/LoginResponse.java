package com.example.employee_detail.enums;

public enum LoginResponse {
    SIGNUP_SUCCESS{
        public String toString(){
            return "Signed up Successfully";
        }
    },
    SIGNUP_FAILURE{
        public String toString(){
            return "Mail id already exists";
        }
    },
    SIGNIN_SUCCESS{
        public String toString(){
            return "Logged in Successfully";
        }
    },
    SIGNIN_FAILURE{
        public String toString(){
            return "Invalid mail id or password";
        }
    },
    MAIL_NOT_FOUND{
        public String toString(){
            return "Mail id not found";
        }
    },
    SUCCESSFUL_SAVE{
        public String toString(){
            return "Data successfully saved";
        }
    },
    SUCCESSFUL_DELETION{
        public String toString(){
            return "Deleted the table data successfully";
        }
    },
    DELETION_FAILURE{
        public String toString(){
            return "Error in deleting the data! Incorrect Password.";
        }
    }
}
