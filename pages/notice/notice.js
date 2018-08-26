Page({
  data: {
    time: "2018-6-10 19:17",
    content: ""
  },
  onLoad:function(options){
    this.setData({
      time:options.time,
      content:options.content
    })
  }
})
