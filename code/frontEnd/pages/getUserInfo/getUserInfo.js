const app = getApp()
// pages/getUserInfo.js
Page({
  data: {
    name: '刘泽宇',
    studentID: '516030910108',
    nameShow: 'none',
    idShow: 'none',
    identity:null,
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    formId:'',
    openid: app.globalData.openId,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  onShow: function (options) {
    this.setData({
      openid: app.globalData.openId,
    });
    if(app.globalData.identity != 'noExist'){
      wx.redirectTo({
        url: '/pages/home/home',
      })
    }
  },
  formSubmit:function(e) {
    var formId = e.detail.formId;
    console.log("form提交,formId为："+formId);
    this.setData({
      formId:formId
    })
    console.log("存储formId:"+this.data.formId);
    var that = this;
    if (this.data.nameShow == 'none') {
      wx.showModal({
        title: '提示',
        content: '请选择身份',
      })
    }
    else if (this.data.name == '') {
      wx.showModal({
        title: '提示',
        content: '姓名不能为空',
      })
    }
    else if (this.data.idShow == '' && this.data.studentID == '') {
      wx.showModal({
        title: '提示',
        content: '学号不能为空',
      })
    }
    else {
      //向后端发送数据
      var isStudent = false;
      if (that.data.idShow == '') {
        isStudent = true
      }
      wx.request({
        url:'http://localhost:8080/login',
        data: {
          openid: app.globalData.openId,
          opengid: app.globalData.openGId,
          name: that.data.name,
          studentID: that.data.studentID,
          isStudent: isStudent,
          formId:that.data.formId
        },
        method: 'POST',
        header: { 'content-type': 'application/json' },
        success: function (res) {
          console.log("存储信息成功")
        },
        fail: function (error) {
          console.log("存储信息失败")

        }
      })
      var data = {
        openid: app.globalData.openId,
        opengid: app.globalData.openGId,
        name: that.data.name,
        studentID: that.data.studentID,
        isStudent: isStudent
      }

    }

  },
  confirm: function () {
    var that = this;
    if(this.data.nameShow == 'none'){
      wx.showModal({
        title: '提示',
        content: '请选择身份',
      })
    }
    else if(this.data.name==''){
      wx.showModal({
        title: '提示',
        content: '姓名不能为空',
      })
    }
    else if (this.data.idShow == '' && this.data.studentID == ''){
      wx.showModal({
        title: '提示',
        content: '学号不能为空',
      })
    }
    else{
      //向后端发送数据
      var isStudent = false;
      if (that.data.idShow == '') {
        isStudent = true
      }
      var data = {
        openid: app.globalData.openId,
        opengid: app.globalData.openGId,
        name: that.data.name,
        studentID: that.data.studentID,
        isStudent: isStudent
      }
      
    }
    
  },
  showPrePage: function () {
    this.setData({
      eye: false
    })
  },
  myOpenSetting: function(){
    var that = this;
    wx.openSetting({
      success: function (res) {
        if (!res.authSetting['scope.userLocation']) {
          that.showRemind();
        }
      },

      complete: function (res) { },
    })
  },
  showRemind: function(){
    var that = this
    wx.showModal({
      title: '温馨提醒',
      content: '需要获取您的地理位置才能使用小程序',
      showCancel: false,
      confirmText: '获取位置',
      success: function (res) {
        if (res.confirm) {
          that.getAuthor();
        }
      }, fail: (res) => {
        that.getAuthor();
      }
    })
  },
  myGetLocation: function(){
    var that = this
    wx.getLocation({
      success: function (res) {
        app.globalData.longitude = res.longitude
        app.globalData.longitude = res.latitude
        console.log(res.longitude)
        console.log(res.latitude)
        
      },
      fail: function () {
        wx.getSetting({
          success(res) {
            if (!res.authSetting['scope.userLocation']) {
              that.myOpenSetting();
            }
          }
        })
      }
    })
  },
  radioChange: function (e) {
    var identity = e.detail.value
    console.log(identity)
    if(e.detail.value=="学生"){
      this.setData({
        nameShow:'',
        idShow:''
      })
    }
    else{
      this.setData({
        nameShow: '',
        idShow: 'none'
      })
    }
    this.setData({
      identity: identity
    })
  },
  getName:function(e){
    var name = e.detail.value;
    console.log(name);
    this.setData({
      name:name
    })
  },
  getID:function(e){
    var id = e.detail.value
    console.log(id)
    this.setData({
      studentID:id
    })
  }
})