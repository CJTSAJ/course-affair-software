Page({
  data: {
    time: "",
    content: "",
    name: ""
  },
  onLoad:function(options){
    this.setData({
      time:options.time,
      content:options.content,
      name: options.name
    })
  }
})
