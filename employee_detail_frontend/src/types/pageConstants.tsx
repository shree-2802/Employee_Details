export type inputFieldProps = {
    autoComplete?: "false" | "true",
    autoCorrect?: "off" | "on",
    spellCheck?: "true" | "false",
    value?: string | number,
    onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
    placeholder?: string,
    pattern?: string,
    type?: string,
    customClassName?: string,
    label?: string,
    icon?: React.ReactElement
}

export type buttonProps = {
    bottonProp: {
        text: string,
        customClass?: string;
        onClick?: (e: any) => void
    }
}

export type securityMeasure = {
    heading: string,
    securityImage: string,
    input: inputFieldProps[],
    resend: string,
    submit: string
}

export type inputField = {
    inputProps: inputFieldProps
}
export type inputContainerProps = {
    inputContent: inputFieldProps[],
    customClassNameContainer?: string,
    customClassNameInput?: string
}

export type inputContainer = {
    inputProp: inputContainerProps
}

export type textCharacteristics = {
    text: string,
    clickable?: true | false,
    onClick?: (e: any) => void,
    customClass?: string
}

export type textProps = {
    text: textCharacteristics[],
    customClassNameContainer?: string
}

export type separatedTextProps = {
    textContext: textProps
}

export interface login {
    backGroundImage: string,
    loginText: string,
    inputContent: inputFieldProps[],
    bottomText: textCharacteristics[],
    buttonText: string
}