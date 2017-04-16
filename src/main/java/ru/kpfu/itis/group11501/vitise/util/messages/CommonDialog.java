package ru.kpfu.itis.group11501.vitise.util.messages;

import java.util.Date;

/**
 * Author: Svintenok Kate
 * Date: 06.04.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface CommonDialog extends Comparable {

    Long getId();

    String getName();

    CommonMessage getLastMessage();

    boolean isConversation();

    Date getCreationDate();

    int getNewMessagesCount();

    Date getReadingLogDate();

    default int compareTo(Object o) {
        CommonDialog dialog = (CommonDialog) o;
        Date date1 = this.getCreationDate();
        Date date2 = dialog.getCreationDate();

        if (this.getLastMessage() != null)
            date1 = this.getLastMessage().getDate();
        if (dialog.getLastMessage() != null)
            date2 = dialog.getLastMessage().getDate();

        if (date1.after(date2)) {
            return -1;
        } else if (date1.before(date2)) {
            return 1;
        }
        return 0;
    }
}
