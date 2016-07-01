package mx.saudade.discovermusicapp.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by angie on 6/30/16.
 */
public class Country {

    private String code;

    private String name;

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null
                || !(o instanceof Country)
                || ((Country) o).code ==null) {
            return false;
        }
        Country that = (Country) o;
        return new EqualsBuilder()
                .append(this.code, that.code)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 17)
                .append(code)
                .hashCode();
    }

    @Override
    public String toString() {
        return code + " - " + name;
    }

}
