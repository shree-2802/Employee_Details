import { ChangeEvent, MouseEvent, useEffect, useState } from "react";
import { textConstants } from "../../constants/page";
import InputContainer from "../../container/input";
import { useNavigate } from "react-router-dom";

//stylesheet
import styles from "./index.module.scss"
import SeparatedText from "../../components/separatedText";
import Button from "../../components/button";

const Login = () => {
    const { Login } = textConstants;
    const [mail, setMail] = useState("");
    const [password, setPassword] = useState("");
    const [forgotPasswordBool, setForgetPasswordBool] = useState<boolean>(false);
    const [showPasswordBool, setShowPasswordBool] = useState<boolean>(false);
    const navigate = useNavigate();

    const onMailChange = (e: ChangeEvent<HTMLInputElement>) => {
        console.log(e.target.value);
        setMail(e.target.value);
    }

    const onPasswordChange = (e: ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    }

    const showPassword = (e: React.MouseEvent<HTMLElement>) => {
        e.preventDefault();
        console.log("show password ", password);
        if (showPasswordBool) {
            Login.inputContent[1].type = "text";
        }
        else {
            Login.inputContent[1].type = "password";
        }
        setShowPasswordBool(prevState => !prevState);
    }

    Login.inputContent.forEach(item => {
        item.customClassName = `${styles.loginInput}`;
        if (item.label === "mail id") {
            item.value = mail;
            item.onChange = onMailChange;
        }
        else {
            item.onChange = onPasswordChange;
            item.value = password;
            item.type = showPasswordBool ? "text" : "password";
        }
    })

    const forgotPassword = (e: React.MouseEvent<HTMLElement>) => {
        navigate("/forgotPassword")
    }

    Login.bottomText.forEach(item => {
        console.log("this is the onclick set for forget password")
        if (item.text === "forgot password") {
            item.onClick = forgotPassword;
        }
        else {
            item.onClick = showPassword;
        }
    }
    )

    useEffect(() => {
        console.log(mail, " swdigw ", password);
    }, [mail, password])

    return (
        <div className={styles.login}>
            <div className={styles.imageContainer}>
                <img src={Login.backGroundImage} alt="Login Background" />
            </div>
            <div className={styles.loginContent}>
                <h1>{Login.loginText}</h1>
                <InputContainer inputProp={{
                    inputContent: Login.inputContent,
                    customClassNameContainer: `${styles.inputContainer}`,
                }} />
                <SeparatedText textContext={{
                    text: Login.bottomText,
                }} />
                <Button bottonProp={{
                    text: Login.buttonText
                }
                } />
            </div>
        </div>
    )
}

export default Login
