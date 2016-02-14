/**
 * Created by User on 2/13/2016.
 */
$(document).ready(function() {


    /* initialize the external events
     -----------------------------------------------------------------*/

    $('#external-events .fc-event').each(function() {

        // store data so the calendar knows to render an event upon drop
        $(this).data('event', {
            title: $.trim($(this).text()), // use the element's text as the event title
            stick: true // maintain when user navigates (see docs on the renderEvent method)
        });

        // make the event draggable using jQuery UI
        $(this).draggable({
            zIndex: 999,
            revert: true,      // will cause the event to go back to its
            revertDuration: 0  //  original position after the drag
        });



    });


    /* initialize the calendar
     -----------------------------------------------------------------*/

    //var calendar = $('#calendar').fullCalendar('getCalendar');
    //var m = calendar.moment();
    //var d = m.duration();
    //var e = m + d;



    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        editable: true,
        droppable: true, // this allows things to be dropped onto the calendar
        drop: function() {
            // is the "remove after drop" checkbox checked?
            if ($('#drop-remove').is(':checked')) {
                // if so, remove the element from the "Draggable Events" list
                $(this).remove();
            }
        },
        eventReceive: function(event){
            var title = event.title;
            var start = event.start.format("YYYY-MM-DD[T]HH:MM:SS");
            $.ajax({
                data: 'type=new&title='+title+'&startdate='+start,
                type: 'POST',
                dataType: 'json',
                success: function(response){
                    event.id = response.eventid;
                    $('#calendar').fullCalendar('updateEvent',event);
                    console.log(event.id)
                    console.log(event)
                },
                error: function(e){
                    //console.log(e.responseText);
                }
            });
            $('#calendar').fullCalendar('updateEvent',event);
        }



    });


});