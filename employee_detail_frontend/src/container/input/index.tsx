import Input from "../../components/inputField"
import { inputContainer, inputFieldProps } from "../../types/pageConstants"
import styles from "./index.module.scss"

const InputContainer = ({ inputProp }: inputContainer): JSX.Element => {
  console.log(inputProp.inputContent[0].onChange);
  return (
    <div className={inputProp?.customClassNameContainer}>
      {
        inputProp.inputContent.map((inputItem: inputFieldProps, ind) => (
          <div
            className={inputItem.icon ? styles.inputBoxWithIcon : styles.inputBox}
            key={ind}
          >
            <Input inputProps={inputItem} />
            {
              inputItem?.icon
            }
          </div>

        ))
      }
    </div>
  )
}

export default InputContainer
