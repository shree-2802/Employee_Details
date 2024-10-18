import { buttonProps } from "../../types/pageConstants";
import { titleFunction } from "../../utils";
import styles from "./index.module.scss";


const Button = ({ bottonProp }: buttonProps) => {
  return (
    <button
      className={bottonProp.customClass ? bottonProp.customClass : styles.button}
      onClick={bottonProp.onClick}
    >
      {titleFunction(bottonProp.text)}
    </button>
  )
}

export default Button
