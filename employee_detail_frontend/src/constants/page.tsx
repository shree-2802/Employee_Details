import { icons, images } from "../assets";
import { login, securityMeasure } from "../types/pageConstants";

const Login: login = {
    backGroundImage: images.loginBackground,
    loginText: "Login",
    inputContent: [
        {
            label: "mail id",
            icon: <icons.IoMdPerson height={27} color="rgb(79, 139, 167)" />,
            placeholder: "mail id"
        }, {
            label: "password",
            icon: <icons.IoMdLock height={27} color="rgb(79, 139, 167)" />,
            placeholder: "password",
            type: "password"
        }
    ],
    bottomText: [
        { text: "forgot password", clickable: true },
        { text: "show password", clickable: true }
    ],
    buttonText: "login"
}

const securityMeasures:securityMeasure = {
    heading: "security measures",
    securityImage: images.lock,
    input: [{
        autoComplete: "false",
        autoCorrect: "off",
        spellCheck: "true",
        placeholder: "enter your email",
        type: "email",
        label: "Email"
    }
        , {
        autoComplete: "false",
        autoCorrect: "off",
        spellCheck: "true",
        placeholder: "Enter OTP sent to your email",
        pattern: "0-9",
        type: "password",
        label: "OTP"
    }
    ],
    resend: "Resend OTP",
    submit: "Submit"
}
export const textConstants = {
    Login,
    securityMeasures
}