var apiclient = (function () {
    var url=window.location.href+'/taller';
    function addMessage(){
        var mensaje=document.getElementById("Message").value;
        axios.post(url,mensaje)
            .then(res => {
                getMessages();
            })
    }
    function getMessages(){
        $("#Table > tbody").empty();
        axios.get(url).then(res=>{
            res.data.map(message=>{
                $("#Table > tbody").append(
                    "<tr>" +
                    "<td>" + message.message + "</td>" +
                    "<td>" + message.date + "</td> " +
                    "</tr>"
                );
            })
        })
    }
    return {
        addMessage:addMessage,
        getMessages:getMessages
    };
})();