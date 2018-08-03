Page({
  data: {
    time0: "",
    content0: ""
  },
  onShow: function (options) {
    this.setData({
      time: options.time,
      content: options.content
    })
  }
})