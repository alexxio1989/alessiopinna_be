package com.example.alessiopinnabe.mapper;

import com.example.alessiopinnabe.dto.PrenotazioneDto;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Arrays;
import java.util.Date;

public class CalendarMapper {
    public static Event getEventFromDto(PrenotazioneDto prenotazioneDto){

        Date startDate = new Date(prenotazioneDto.getDataPrenotazione().getTime());
        Date endDate = DateUtils.addHours(startDate, prenotazioneDto.getQntOre().intValue());

        Event event = new Event()
                .setSummary("alessiopinna.it :: Lezione di " + prenotazioneDto.getCorso().getTitolo())
                .setLocation("ONLINE")
                .setDescription("Prenotazione di "+ prenotazioneDto.getQntOre() + " ore per la lezione di " + prenotazioneDto.getCorso().getTitolo());

        DateTime startDateTime = new DateTime(startDate);
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime);
        event.setStart(start);

        DateTime endDateTime = new DateTime(endDate);
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime);
        event.setEnd(end);

        EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(15),
                new EventReminder().setMethod("popup").setMinutes(10),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));

        return event;
    }

}
