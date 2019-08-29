/**
 * 工具类
 * @author JasonTsangLai
 * @date 2019-04-13
 */
class Utils{

    /**
     * 获取字符串的字符长度
     * @param str
     * @returns Number
     * @author JasonTsangLai
     * @date 219-04-13
     */
    static getCharLen(str) {
        var len = 0;
        for (var i = 0; i < str.length; i++) {
            var a = str.charAt(i);
            if (a.match(/[^\x00-\xff]/ig) != null) {
                len += 3;
            }
            else {
                len += 1;
            }
        }
        return len;
    }

}