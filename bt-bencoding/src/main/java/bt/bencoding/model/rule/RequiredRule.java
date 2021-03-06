package bt.bencoding.model.rule;

import bt.bencoding.model.ClassUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Used to mark some attribute of an object model as required.
 *
 * @since 1.0
 */
public class RequiredRule implements Rule {

    private List<String> requiredKeys;

    /**
     * @param requiredKeys List of required attributes (in the order in which attributes should be checked).
     * @since 1.0
     */
    public RequiredRule(List<String> requiredKeys) {
        this.requiredKeys = requiredKeys;
    }

    @Override
    public boolean validate(Object object) {

        Map map;
        try {
            map = ClassUtil.cast(Map.class, null, object);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected validation exception", e);
        }

        for (String requiredKey : requiredKeys) {
            if (!map.containsKey(requiredKey)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "properties are required: " + Arrays.toString(requiredKeys.toArray());
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
