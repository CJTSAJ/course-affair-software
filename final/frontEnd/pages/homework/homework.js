Page({
  data: {
    time: "",
    content: "",
    deadline: ""
  },
  onLoad: function (options) {
    var ddl = options.deadline.replace(/-/g, '/')
    /*var newDate = new Date();
    var timestamp = Date.parse(options.deadline);
    newDate.setTime(timestamp);
    var ddl = newDate.toLocaleDateString() + newDate.toLocaleTimeString()*/
    this.setData({
      time: options.time,
      content: options.content,
      deadline: ddl,
    })
  }
})