var apiclient = (function () {
    var url=window.location.href+'/taller';
    function addMessage(){
        var mensaje=document.getElementById("Message").value;
        const headers = {
            'Content-Type': 'application/json'
        }
        axios.post(url,mensaje,headers)
            .then(res => {
                getMessages();
            })
    }
    function getMessages(){
        $("#Table > tbody").empty();
        axios.get(url).then(res=>{
            console.log(res.data)
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