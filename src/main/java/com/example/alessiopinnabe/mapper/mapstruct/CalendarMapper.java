package com.example.alessiopinnabe.mapper.mapstruct;

import com.example.alessiopinnabe.dto.AcquistoDto;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import org.apache.commons.lang3.time.DateUtils;
import org.mapstruct.Mapper;

import java.util.Arrays;
import java.util.Date;

@Mapper
public interface CalendarMapper {
    default Event getEventFromDto(AcquistoDto prenotazioneDto){

        Date startDate = new Date(prenotazioneDto.getDataAcquisto().getTime());
        Date endDate = DateUtils.addHours(startDate, prenotazioneDto.getQuantita().intValue());

        Event event = new Event()
                .setSummary("alessiopinna.it :: Lezione di "
                        //+ prenotazioneDto.getProdotto().getNome()
                )
                .setLocation("ONLINE")
                .setDescription("Prenotazione di "+ prenotazioneDto.getQuantita() + " ore per la lezione di "
                        // + prenotazioneDto.getProdotto().getNome()
                );

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
        event.setReminders(reminders);
        return event;
    }

}
