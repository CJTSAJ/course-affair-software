Page({
  data: {
    time: "2018-6-10 19:17",
    content: "由于我要参加科教文组织举办的一个在线教育相关会议并作交流，经向学校教务处申请，并获得批准，原定6月12日上午3-4节的数据库课程，调整至6月15日上午1-2节，上课地点仍在上院315。\n\n请相互转告。\n\n对由此给你带来的不便，我表示歉意。"
  },
  onLoad:function(options){
    this.setData({
      time:options.time,
      content:options.content
    })
  }
})
