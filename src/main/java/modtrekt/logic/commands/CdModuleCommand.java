package modtrekt.logic.commands;

import com.beust.jcommander.Parameter;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.model.Model;
import modtrekt.model.module.ModCode;

/**
 * Changes the current module that is shown to the user.
 */
public class CdModuleCommand extends Command {
    public static final String COMMAND_WORD = "cd";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <module code>: cds into specified module.\n"
            + COMMAND_WORD + " ..: cds out of current module.";

    @Parameter(description = "<module code>: the module code to cd into, or '..' to cd out.",
            required = true)
    private String argument;

    /**
     * Creates an instance of CdModuleCommand, for use with JCommander.
     */
    public CdModuleCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (argument.equals("..")) {
            ModCode previousCode = model.getCurrentModule();
            if (previousCode == null) {
                throw new CommandException("Already showing all modules.");
            }
            model.setCurrentModule(null);
            return new CommandResult(String.format(
                    "Exited module %s, now showing all modules.", previousCode));
        }
        // Check if argument is a valid module code
        try {
            ModCode moduleCode = new ModCode(argument);
            if (!model.hasModuleWithModCode(moduleCode)) {
                throw new CommandException(String.format("Module code %s does not exist.",
                        moduleCode.toString()));
            }
            model.setCurrentModule(moduleCode);
            return new CommandResult(String.format("Changed current module to %s!", moduleCode.toString()));
        } catch (IllegalArgumentException exception) {
            throw new CommandException(String.format("%s is not a valid module code. Usage:\n%s",
                    MESSAGE_USAGE));
        }
    }
}
