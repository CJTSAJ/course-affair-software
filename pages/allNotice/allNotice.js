const app = getApp()
Page({
  data:{
    allContent: ["由于我要参加科教文组织举办的一个在线教育相关会议并作交流，经向学校教务处申请，并获得批准，原定6月12日上午3-4节的数据库课程，调整至6月15日上午1-2节，上课地点仍在上院315。\n\n请相互转告。\n\n对由此给你带来的不便，我表示歉意。", "定于6月8日（周五），在中院205继续进行课程项目的分组交流，具体安排如下：\n1）时间：\n第一批次，上午08: 00 - 09:40\n第二批次，下午14: 00 - 15:40\n第三批次，下午16: 00 - 18:00\n2）地点：\n中院205\n3）其他：\n5月24日、6月5日、6月7日的课程时间，调整用于6月8日的课程项目汇报、演示、交流。\n\n请相互转告。\n\nJBoss"],
    time0:"2018-6-10 19:17",
    time1: "2018-6-10 20:08"
  },
  toDetail:function(){
    wx.navigateTo({
      url: '/pages/notice/notice',
    })
  },
  toEdit:function(){
    wx.redirectTo({
      url: '/pages/addNotice/addNotice',
    })
  },
  onShow: function(){
    var self = this;
    var openid = app.globalData.openId;
    console.log("openid:" + openid);
    var openidData = {
      'opengid': openid
    }
    wx.request({
      url: 'http://127.0.0.1:8080/hibernate/getNotice',
      data: openid,
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        console.log("content::" + res.data[0])
        self.setData({
          
          allContent: res.data
        })
      },
      fail: function (error) {
      }
    })
  }
})