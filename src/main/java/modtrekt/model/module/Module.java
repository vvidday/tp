package modtrekt.model.module;

import java.util.Objects;

/**
 * Represents a module in the module list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {
    public final ModCode code;
    public final ModName name;
    public final ModCredit credits;

    /**
     * Creates a Module with the given code, name, credits and tasks.
     *
     * @param code    the module code
     * @param name    the module name
     * @param credits the number of credits for the module
     */
    public Module(ModCode code, ModName name, ModCredit credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public ModName getName() {
        return name;
    }

    public ModCode getCode() {
        return code;
    }

    public ModCredit getCredits() {
        return credits;
    }

    /**
     * Returns true if both modules have the same name.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getName().equals(getName());
    }

    /**
     * Returns true if both modules have the same identity and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getName().equals(getName())
                && otherModule.getCode().equals(getCode())
                && otherModule.getCredits() == getCredits();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, code, credits);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Code: ")
                .append(getCode())
                .append("; Credits: ")
                .append(getCredits());

        return builder.toString();
    }
}