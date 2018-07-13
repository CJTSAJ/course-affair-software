const app = getApp()
Page({
  data:{
    allHomework: [],
    grade0: "此作业成绩尚未发布",
    grade1: "95"
  },
  toDetail(){
    wx.navigateTo({
      url: '/pages/homework/homework',
    })
  },
  getGrade:function(e){
    var index = e.target.dataset.id;
    var homeworkID = this.data.allHomework[index][3];

    console.log(homeworkID);

    wx.request({
      url: 'http://localhost:8080/getHwGrade',
      data:{
        openid: app.globalData.openId,
        openGId: app.globalData.openGId,
        homeworkid: homeworkID
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(res){
        if(!res.data){
          wx.showModal({
            title: '提示',
            content: '此次成绩未发布',
          })
        }else{
          wx.showModal({
            title: '提示',
            content: res.data,
          })
        }
      },
      fail:function(error){

      }
    })
  },
  releaseHomework:function(){
    wx.navigateTo({
      url: '/pages/releaseHomework/releaseHomework',
    })
  },
  onShow: function(){
    var self = this;
    wx.request({
      url: 'http://localhost:8080/getHomework',
      data:{
        openGId: app.globalData.openGId
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(res){
        self.setData({
          allHomework: res.data
        })
        console.log("success:" + self.data.allHomework)
      },
      fail:function(error){

      }
    })
  }
})