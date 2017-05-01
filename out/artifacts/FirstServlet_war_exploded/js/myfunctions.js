/**
 * Created by kanhu on 1/5/17.
 */

/*$(document).ready(function () {
    $("form").submit(function () {
        keyName = $('#key').val();
        passPhrase = $('#passphrase').val();
        cPassPhrase = $('#cpassphrase').val();

        var keyPhrase = {"keyName": keyName, "passPhrase": passPhrase, "cPassPhrase": cPassPhrase};

        console.log(keyPhrase);
        $.ajax({
            url: '/welcome',
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(keyPhrase),
            contentType: 'application/json',
            mimeType: 'application/json',

            success: function () {
                alert("success");
            },
            failure: function() {
                alert("failure");
            }
        });
    });
});*/
function sendAjax() {

    // get inputs
    keyName = $('#key').val();
    passPhrase = $('#passphrase').val();
    cPassPhrase = $('#cpassphrase').val();

    var keyPhrase = {"keyName": keyName, "passPhrase": passPhrase, "cPassPhrase": cPassPhrase};

    $.ajax({
        url: "welcome",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(keyPhrase),
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function () {
            alert("success");
        },
        failure: function() {
            alert("failure");
        }
    });
}

console.log("fine");
