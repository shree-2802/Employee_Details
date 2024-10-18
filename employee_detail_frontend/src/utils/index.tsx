export const titleFunction = (word: string | undefined) => {
    if (word === undefined) {
        return "";
    }
    let result = "";
    result += word.charAt(0).toUpperCase();
    for (let i = 1; i < word.length - 1; i++) {
        if (word.charAt(i) === ' ') {
            result += ' ' + word.charAt(i + 1).toUpperCase();
            i++;
            continue;
        }
        result += word.charAt(i);
    }
    result += word.charAt(word.length - 1);
    return result;
}