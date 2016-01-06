/**
 * Created by User on 1/6/2016.
 */

function totalNights(price){
    var checkin = new Date(document.getElementById('checkIn').value);
    var checkout = new Date(document.getElementById('checkOut').value);
    var timeDiff = Math.abs(checkout.getTime() - checkin.getTime());
    var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));

    $('#total').text(diffDays);



    var money = diffDays *  price;

    $('#totalmoney').text(money);
}
function capacity(){
    var person = document.getElementById("person").value;
    $('#nopersons').text(person);
}
