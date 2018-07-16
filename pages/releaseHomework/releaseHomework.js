const app = getApp()

// pages/releaseHomework/releaseHomework.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    dateValue: "年/月/日",
    timeValue: "时/分",
    homeworkContent: ""
  },

  datePickerBindchange: function (e){
    this.setData({
      dateValue: e.detail.value
    })
  },
  timePickerBindchange:function(e){
    this.setData({
      timeValue: e.detail.value
    })
  },

  /*textarea输入响应事件*/
  getContent:function(e){
    this.setData({
      homeworkContent: e.detail.value
    })
  },

  /*发布作业按钮响应事件*/
  release: function(){
    if (this.data.homeworkContent == ""){
      wx.showModal({
        title: '提示',
        content: '内容不能为空',
      })
    }
    else if(this.data.dateValue == "年月日" || this.data.timeValue == "时/分"){
      wx.showModal({
        title: '提示',
        content: '请选择截止日期',
      })
    }
    else{
      var self = this;
      wx.showModal({
        title: '提示',
        content: '您确定要发布本次作业？',
        success:function(res){
          if(res.confirm){
            self.confirmRelease(self);
          }
        }
      })
      
    }
  },
  confirmRelease:function(self){
    var deadline = this.data.dateValue + " " + this.data.timeValue;
    console.log(deadline);
    console.log(app.globalData.openGId);
    wx.request({
      url: 'http://207.148.114.118:8080/hibernateSpringDemo/addHomework',
      data: {
        content: self.data.homeworkContent,
        openid: app.globalData.openId,
        openGId: app.globalData.openGId,
        deadline: deadline
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        console.log("add homework successfully");
        wx.navigateBack({
          delta: 1
        })
      },
      fail: function (error) {

      }
    })
  }
})