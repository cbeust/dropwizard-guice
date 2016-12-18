
var app = new Vue({
    el: '#app',
    data: {
        date: new Date(),
        vs: []
    },
    created: function () {
        var self = this;
        $.ajax({
            url: '/v0/getVideos',
            type: 'get',
            context: this,
            dataType: 'json',
            success: function (videos) {
                var self = this;
                this.vs = videos;
            }
        })
    }
})





