import { ChangeEvent, useState } from "react";
import { images } from "../../assets"
import { textConstants } from "../../constants/page";

//styles
import styles from "./index.module.scss";
import Input from "../../components/inputField";

const ForgotPassword = () => {
    const { securityMeasures } = textConstants;
    const [mail, setMail] = useState("");
    const [otp, setOTP] = useState<number | null>();

    const onMailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setMail(e.target.value);
        console.log(e.target.value);
    }

    const onOTPChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        console.log("otpchn", Number(e.target.value.at(-1)));
        if (e.target.value.length !== 0 && isNaN(Number(e.target.value))) {
            alert("You can't enter Characters")
        }
        else {
            setOTP(Number(e.target.value));
        }
        console.log(e.target.value);
    }

    securityMeasures.input.forEach((item, ind) => {
        if (ind === 0) {
            item.value = mail;
            item.onChange = onMailChange;
        }
        else {
            item.value = otp ? otp : "";
            item.onChange = onOTPChange;
        }
    })
    
    return (
        <div className={styles.forgotContainer}>
            <div className={styles.forgotInner}>
                <div className={styles.forgotInnerContent}>
                    <div className={styles.leftContainer}>
                        <img src={images.back} alt="back" className={styles.back} />
                        <div className={styles.imageContainer}>
                            <img src={images.lock} alt="security measures" />
                        </div>
                    </div>
                    <div className={styles.forgotInput}>
                        <p>{securityMeasures.heading}</p>
                        <form>
                            {securityMeasures.input.map((item, ind) => {
                                return (
                                    <div key={ind}>
                                        <label>{item.label}</label>
                                        <Input inputProps={item} />
                                    </div>
                                )
                            })}
                        </form>
                    </div>
                </div>
                <img className={styles.touch} src={images.touch} alt="" />
            </div>
        </div>
    )
}

export default ForgotPassword
