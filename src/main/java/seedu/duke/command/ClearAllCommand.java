package seedu.duke.command;

import seedu.duke.exceptions.DukeExceptions;
import seedu.duke.exceptions.EmptyListsException;
import seedu.duke.storage.AppointmentList;
import seedu.duke.storage.PatientList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.text.ParseException;

/**
 * Clear all the patients from patients' list and appointments from appointments' list
 *
 * @author Nguyen Thanh Duc
 * @version 2.0
 * @since 2020-03-29
 */
public class ClearAllCommand extends Command {

    public static final String COMMAND_WORD = "clearall";
    public static final String EXAMPLE = "clearall";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clear all patients and appointments from both lists.\n"
            + "Example: " + EXAMPLE;

    /**
     * Method to clear all the items in both lists if available, if
     * there is nothing to clear return a warning to users
     *
     * @param ui      the ui object which can be used to display text
     * @param storage the storage object for auto saving function
     * @throws IOException
     * @throws ParseException
     * @see IOException
     * @see ParseException
     * @see DukeExceptions#checkEmptyLists
     * @see AppointmentList#getAppointmentList
     * @see PatientList#getPatientList
     * @see Storage#saveAppointmentsList
     * @see Storage#savePatientList
     */
    @Override
    public void execute(Ui ui, Storage storage) throws IOException, ParseException {
        try {
            //Check if the list is empty or not
            DukeExceptions.checkEmptyLists();

            // Clear all the items in appointments' list
            AppointmentList.getAppointmentList().clear();

            // Clear all the items in patients' list
            PatientList.getPatientList().clear();

            // Make sure that the size of both patients' list and appointments' list are 0
            assert AppointmentList.getTotalAppointments() == 0 && PatientList.getTotalPatients() == 0;

            // Auto save the change in appointments' list
            storage.saveAppointmentsList();

            // Auto save the change in patients' list
            storage.savePatientList();

            // Show all items in both lists deleted message
            // ui.showAllItemsDeleted(); //TODO Justin
        } catch (EmptyListsException e) {
            System.out.println("There is nothing to clear in both lists");
            // ui.showNothingToClearBothLists(); // TODO Justin
        }
    }
}
