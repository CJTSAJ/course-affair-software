Page({
  data: {
    time: "2018-6-10 19:17",
    content: ""
  },
  onShow:function(options){
    this.setData({
      time:options.time,
      content:options.content
    })
  }
})
