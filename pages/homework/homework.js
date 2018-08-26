Page({
  data: {
    time: "",
    content: ""
  },
  onLoad: function (options) {
    this.setData({
      time: options.time,
      content: options.content
    })
  }
})