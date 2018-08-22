const app = getApp()
// pages/allFile/allFile.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    allFile: [],
    fileForm: []
  },
  download:function(e){
    var self = this;
    var index = e.currentTarget.dataset.id;
    console.log(index);
    var objectid = this.data.allFile[index].objectId
    console.log(objectid);
    wx.setClipboardData({
      data: 'http://207.148.114.118:8080/courseAffair/downloadFile?objectid=' + objectid,
      success: function(res){
        wx.getClipboardData({
          success: function(res){
            wx.showModal({
              title: "提示",
              content: "下载地址已复制，请通过浏览器下载"
            })
          }
        })  
      }
    })
    /*wx.downloadFile({
      url:'http://207.148.114.118:8080/courseAffair/downloadFile?objectid=' + objectid,
      success: function(res){
        var filePath = res.tempFilePath;
        console.log(filePath);
        console.log(res);
        wx.openDocument({
          filePath: filePath,
          success: function(res){

          }
        })
      }
    })*/
  },
  onShow:function(){
    var self = this;
    wx.request({
      url: 'http://207.148.114.118:8080/courseAffair/getAllFile',
      data: {
        opengid: '123'
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(res){
        console.log(res.data);
        var temp = res.data[0].filename.split('.');
        var houzhui = temp[temp.length - 1]
        console.log("后缀" + houzhui);
        res.data.reverse();
        var arr = res.data;
        var len = arr.length;

        var tempPictureForm = [];
        for(var i = 0; i < len; i++){
          var temp = res.data[i].filename.split('.');
          var houzhui = temp[temp.length - 1];

          //arr[i].updateDate = arr[i].updateDate.slice(0, 19);
          var date = arr[i].uploadDate.slice(0, 19);
          arr[i].uploadDate = date.replace('T', ' ');
          switch(houzhui){
            case 'mp3':
            case 'wav':
            case 'wma':
            case 'midi':
              tempPictureForm.push("/images/mp3.png");
              break;
            case 'mp4':
            case 'mov':
            case 'wmv':
            case 'mkv':
            case 'flv':
            case 'avi':
            case 'mpeg':
            case 'rm':
            case 'rmvb':
              tempPictureForm.push("/images/mp4.png");
              break;
            case 'pdf':
              tempPictureForm.push("/images/pdf.png");
              break;
            case 'doc':
            case 'docx':
              tempPictureForm.push("/images/word.png");
              break;
            case 'txt':
              console.log("txt");
              tempPictureForm.push("/images/txt.png");
              break;
            case 'ppt':
            case 'pptx':
              console.log("pptx");
              tempPictureForm.push("/images/ppt.png");
              break;
            case 'gif':
            case 'png':
            case 'jpg':
            case 'jpeg':
            case 'bpm':
              console.log("gif");
              tempPictureForm.push("/images/picture.png");
              break;
            default:
              console.log("default");
              tempPictureForm.push("/images/file.png");
          }
        }
        self.setData({
          allFile: arr,
          fileForm: tempPictureForm
        })
      }
    })
  },
  toUploadFile:function(){
    wx.navigateTo({
      url: '/pages/uploadFile/uploadFile',
    })
  }
})