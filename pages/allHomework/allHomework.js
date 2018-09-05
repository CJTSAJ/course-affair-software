const app = getApp()
Page({
  data:{
    allHomework: [],
    grade0: "此作业成绩尚未发布",
    grade1: "95",
    isTeacher: false
  },
  getGrade:function(e){
    var index = e.target.dataset.id;
    var homeworkID = this.data.allHomework[index][3];

    console.log(homeworkID);

    wx.request({
      url: app.globalData.serverUrl + 'getHwGrade',
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
    if(app.globalData.identity != "student"){
      this.setData({
        isTeacher: true
      })
    }

    var self = this;
    wx.request({
      url: app.globalData.serverUrl + 'getHomework',
      data:{
        openGId: app.globalData.openGId
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(res){
        if(res.statusCode == 200){
          var temp = res.data;
          temp.reverse();
          self.setData({
            allHomework: temp
          })
          console.log("success:" + self.data.allHomework)
        }else{
          wx.showModal({
            title: '错误',
            content: '服务器发生错误',
          })
        }
        
      },
      fail:function(error){

      }
    })
  }
})