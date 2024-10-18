import { inputField } from "../../types/pageConstants"
import { titleFunction } from "../../utils"

const Input = ({ inputProps }: inputField) => {
  return (
    <input
      value={inputProps.value}
      onChange={inputProps.onChange}
      autoComplete={inputProps?.autoComplete}
      autoCorrect={inputProps?.autoCorrect}
      spellCheck={inputProps?.spellCheck}
      placeholder={titleFunction(inputProps?.placeholder)}
      type={inputProps?.type}
      pattern={inputProps?.pattern}
      className={inputProps?.customClassName}
    />
  )
}

export default Input