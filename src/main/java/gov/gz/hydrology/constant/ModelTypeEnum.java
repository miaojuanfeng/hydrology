package gov.gz.hydrology.constant;

/**
 * {{文件描述}}
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2020年11月24日
 */
public enum ModelTypeEnum {

    XAJ(1, "新安江"),

    LINE(2, "经验单位线"),

    API(3, "API");

    private final Integer id;

    private final String text;

    private ModelTypeEnum(Integer id, String text){
        this.id = id;
        this.text = text;
    }

    public static String getText(Integer id) {
        ModelTypeEnum[] modelTypeEnums = values();
        for (ModelTypeEnum modelTypeEnum : modelTypeEnums) {
            if (modelTypeEnum.id.equals(id)) {
                return modelTypeEnum.text;
            }
        }
        return null;
    }
}
