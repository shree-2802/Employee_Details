import { Fragment } from "react/jsx-runtime";
import { separatedTextProps } from "../../types/pageConstants"
import { titleFunction } from "../../utils";
import Button from "../button";
import styles from "./index.module.scss";

const SeparatedText = ({ textContext }: separatedTextProps) => {

    return (
        <div className={
            textContext.customClassNameContainer ?
                textContext.customClassNameContainer
                :
                styles.separatedText
        }
        >
            {
                textContext.text.map((text, ind) => {
                    return (
                        !text.clickable ?
                            <p
                                className={
                                    text.customClass ? `${text.customClass}` : `${styles.localText}`
                                }
                                key={ind}
                            >
                                {titleFunction(text.text)}
                            </p>
                            :
                            <Button
                                key={ind}
                                bottonProp={{
                                    text: text.text,
                                    customClass: text.customClass ? `${text.customClass}` : `${styles.textButton}`,
                                    onClick: text?.onClick
                                }}
                            />
                    )
                })
            }
        </div>
    )
}

export default SeparatedText
