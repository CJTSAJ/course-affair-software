// pages/editQuestionContent/editQuestionContent.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    key: null,
    questionContent: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.key = options.key;
    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2];
    var questionContent = prevPage.data.questionContent[options.key];
    this.setData({
      key: this.data.key,
      questionContent: questionContent,
    })
    console.log(this.data);
  },

  inputQuestionContent: function(e){
    this.data.questionContent = e.detail.value;
    this.setData({
      questionContent: this.data.questionContent
    })
  },

  confirm: function(){
    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2];
    prevPage.data.questionContent[this.data.key] = this.data.questionContent;
    prevPage.setData({
      questionContent: prevPage.data.questionContent
    });
    wx.navigateBack({
      changed: true 
    });
  },

  cansel: function(){
    wx.navigateBack({
      changed: true
    });
  },

})