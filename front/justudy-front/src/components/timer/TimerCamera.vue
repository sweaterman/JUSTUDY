<template>
    <v-container>
        <v-row>
            <v-col cols="12" md="2" justify="center" align="center">
                <div id="video-container" ref="webcamContainer" style="position: relative" justify="center" align="center">
                    <video ref="webcamElement" autoplay muted playsinline></video>
                </div>
            </v-col>
            <v-col cols="12" md="10" v-if="!isView">
                <!-- 타이머 구현 -->
                <v-row>
                    <hr width="100%" />
                    <v-col cols="12" md="2" justify="center" align="center">
                        <v-img :src="require('@/assets/fire3.gif')" :style="`width: 130px; height: 130px; `" />
                    </v-col>
                    <v-col cols="12" md="8" justify="center" align="center" :style="`font-family: 'Digital Dismay'; font-size : 100px`">
                        {{ timeString }}
                    </v-col>
                    <v-col cols="12" md="2" justify="center" align="center">
                        <v-img :src="require('@/assets/fire3.gif')" :style="`width: 130px; height: 130px; `" />
                    </v-col>
                    <hr width="100%" />
                </v-row>
                <!-- <h1 style="position: relative; z-index: 2">{{ timeString }}</h1> -->
            </v-col>

            <v-col cols="12" md="10" v-if="isView" justify="center" align="center">
                <v-row>
                    <v-col>
                        <h3>
                            <span style="color: black">오늘 </span>
                            <span style="color: #ffb000">공부할 과목</span>
                            <span style="color: black">을 선택한 후 공부하세요!</span>
                        </h3>
                    </v-col>
                </v-row>
                <v-row :style="{marginTop: '0.4%'}">
                    <v-col coks="12" md="2">
                        <v-row justify="center" align="center">
                            <v-btn class="card_section_category" color="white" depressed justify="center" align="center" x-large :style="{marginTop: '5%'}">
                                <input @change="webcamOnOff" value="Front-end" id="a" v-model="webcamSwitch" type="checkbox" v-show="false" />
                                <label class="category" for="a">
                                    <div :style="{fontSize: 'medium'}">Front-end</div>
                                </label>
                            </v-btn>
                        </v-row>
                    </v-col>
                    <v-col coks="12" md="2">
                        <v-row justify="center" align="center">
                            <input @change="webcamOnOff" value="Back-end" id="b" v-model="webcamSwitch" type="checkbox" v-show="false" />
                            <v-btn class="card_section_category" color="white" depressed justify="center" align="center" x-large :style="{marginTop: '5%'}">
                                <label class="category" for="b">
                                    <div :style="{fontSize: 'medium'}">Back-end</div>
                                </label>
                            </v-btn>
                        </v-row>
                    </v-col>
                    <v-col coks="12" md="2">
                        <v-row justify="center" align="center">
                            <input @change="webcamOnOff" value="DataBase" id="c" v-model="webcamSwitch" type="checkbox" v-show="false" />
                            <v-btn class="card_section_category" color="white" depressed justify="center" align="center" x-large :style="{marginTop: '5%'}">
                                <label class="category" for="c">
                                    <div :style="{fontSize: 'medium'}">DataBase</div>
                                </label>
                            </v-btn>
                        </v-row>
                    </v-col>
                    <v-col coks="12" md="2">
                        <v-row justify="center" align="center">
                            <input @change="webcamOnOff" value="ComputerScience" id="d" v-model="webcamSwitch" type="checkbox" v-show="false" />
                            <v-btn class="card_section_category" color="white" depressed justify="center" align="center" x-large :style="{marginTop: '5%'}">
                                <label class="category" for="d">
                                    <div :style="{fontSize: 'medium'}">CS</div>
                                </label>
                            </v-btn>
                        </v-row>
                    </v-col>
                    <v-col coks="12" md="2">
                        <v-row justify="center" align="center">
                            <input @change="webcamOnOff" value="Algorithm" id="e" v-model="webcamSwitch" type="checkbox" v-show="false" />
                            <v-btn class="card_section_category" color="white" depressed justify="center" align="center" x-large :style="{marginTop: '5%'}">
                                <label class="category" for="e">
                                    <div :style="{fontSize: 'medium'}">Algorithm</div>
                                </label>
                            </v-btn>
                        </v-row>
                    </v-col>
                    <v-col coks="12" md="2">
                        <v-row justify="center" align="center">
                            <input @change="webcamOnOff" value="Etc." id="f" v-model="webcamSwitch" type="checkbox" v-show="false" />
                            <v-btn class="card_section_category" color="white" depressed justify="center" align="center" x-large :style="{marginTop: '5%'}">
                                <label class="category" for="f">
                                    <div :style="{fontSize: 'medium'}">Etc.</div>
                                </label>
                            </v-btn>
                        </v-row>
                    </v-col>
                </v-row>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import axios from 'axios';
import port from '@/store/port';
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Webcam {
    constructor(e, t = 'user', s = null, i = null) {
        (this._webcamElement = e),
            (this._webcamElement.width = this._webcamElement.width || 640),
            (this._webcamElement.height = this._webcamElement.height || 0.75 * this._webcamElement.width),
            (this._facingMode = t),
            (this._webcamList = []),
            (this._streamList = []),
            (this._selectedDeviceId = ''),
            (this._canvasElement = s),
            (this._snapSoundElement = i);
    }
    get facingMode() {
        return this._facingMode;
    }
    set facingMode(e) {
        this._facingMode = e;
    }
    get webcamList() {
        return this._webcamList;
    }
    get webcamCount() {
        return this._webcamList.length;
    }
    get selectedDeviceId() {
        return this._selectedDeviceId;
    }
    getVideoInputs(e) {
        return (
            (this._webcamList = []),
            e.forEach(e => {
                'videoinput' === e.kind && this._webcamList.push(e);
            }),
            1 == this._webcamList.length && (this._facingMode = 'user'),
            this._webcamList
        );
    }
    getMediaConstraints() {
        var e = {};
        return '' == this._selectedDeviceId ? (e.facingMode = this._facingMode) : (e.deviceId = {exact: this._selectedDeviceId}), {video: e, audio: !1};
    }
    selectCamera() {
        for (let e of this._webcamList)
            if (('user' == this._facingMode && e.label.toLowerCase().includes('front')) || ('enviroment' == this._facingMode && e.label.toLowerCase().includes('back'))) {
                this._selectedDeviceId = e.deviceId;
                break;
            }
    }
    flip() {
        (this._facingMode = 'user' == this._facingMode ? 'enviroment' : 'user'), (this._webcamElement.style.transform = ''), this.selectCamera();
    }
    async start(e = !0) {
        return new Promise((t, s) => {
            this.stop(),
                navigator.mediaDevices
                    .getUserMedia(this.getMediaConstraints())
                    .then(i => {
                        this._streamList.push(i),
                            this.info()
                                .then(() => {
                                    this.selectCamera(),
                                        e
                                            ? this.stream()
                                                  .then(() => {
                                                      t(this._facingMode);
                                                  })
                                                  .catch(e => {
                                                      s(e);
                                                  })
                                            : t(this._selectedDeviceId);
                                })
                                .catch(e => {
                                    s(e);
                                });
                    })
                    .catch(e => {
                        s(e);
                    });
        });
    }
    async info() {
        return new Promise((e, t) => {
            navigator.mediaDevices
                .enumerateDevices()
                .then(t => {
                    this.getVideoInputs(t), e(this._webcamList);
                })
                .catch(e => {
                    t(e);
                });
        });
    }
    async stream() {
        return new Promise((e, t) => {
            navigator.mediaDevices
                .getUserMedia(this.getMediaConstraints())
                .then(t => {
                    this._streamList.push(t),
                        (this._webcamElement.srcObject = t),
                        'user' == this._facingMode && (this._webcamElement.style.transform = 'scale(-1,1)'),
                        this._webcamElement.play(),
                        e(this._facingMode);
                })
                .catch(e => {
                    console.log(e), t(e);
                });
        });
    }
    stop() {
        this._streamList.forEach(e => {
            e.getTracks().forEach(e => {
                e.stop();
            });
        });
    }
    snap() {
        if (null != this._canvasElement) {
            null != this._snapSoundElement && this._snapSoundElement.play(),
                (this._canvasElement.height = this._webcamElement.scrollHeight),
                (this._canvasElement.width = this._webcamElement.scrollWidth);
            let e = this._canvasElement.getContext('2d');
            return (
                'user' == this._facingMode && (e.translate(this._canvasElement.width, 0), e.scale(-1, 1)),
                e.clearRect(0, 0, this._canvasElement.width, this._canvasElement.height),
                e.drawImage(this._webcamElement, 0, 0, this._canvasElement.width, this._canvasElement.height),
                this._canvasElement.toDataURL('image/png')
            );
        }
        throw 'canvas element is missing';
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
const modelPath = 'https://cdn.jsdelivr.net/gh/cgarciagl/face-api.js/weights/';
const activeTime = 0.23;
export default {
    name: 'FaceRecognition',
    components: {},
    mounted() {
        this.webcam = new Webcam(this.$refs.webcamElement, 'user');
        this.$refs.webcamElement.onloadedmetadata = el => {
            console.log('onloadedmetadata');
            console.log(el.srcElement.scrollWidth);
            this.displaySize = {width: el.srcElement.scrollWidth, height: el.srcElement.scrollHeight};
        };
    },
    data() {
        return {
            port:port,
            size: {height: 0, width: 0},
            isView: true,
            timeString: '',
            time: 0.0,
            transferTime: 0.0,
            webcamSwitch: [],
            boxSwitch: false,
            webcam: null,
            currentStream: null,
            displaySize: null,
            convas: null,
            faceDetection: null
        };
    },
    created() {},
    methods: {
        webcamOnOff() {
            if (this.webcamSwitch.length > 0) {
                this.isView = false;
                console.log(this.webcamSwitch[0]);
                this.webcam
                    .start()
                    .then(result => {
                        console.log('webcam started');
                        console.log(result);
                        Promise.all([
                            window.faceapi.nets.tinyFaceDetector.loadFromUri(modelPath),
                            window.faceapi.nets.faceLandmark68TinyNet.loadFromUri(modelPath),
                            window.faceapi.nets.faceExpressionNet.loadFromUri(modelPath),
                            window.faceapi.nets.ageGenderNet.loadFromUri(modelPath)
                        ]).then(() => {
                            this.createCanvas();
                            this.startDetection();
                        });
                    })
                    .catch(err => {
                        console.log('webcam started err');
                        console.log(err);
                    });
            } else {
                this.cameraStopped();
                this.webcam.stop();
                console.log('webcam stopped');
                clearInterval(this.faceDetection);
                if (typeof this.canvas !== 'undefined' && this.canvas !== null) {
                    setTimeout(function () {
                        this.canvas.getContext('2d').clearRect(0, 0, this.canvas.width, this.canvas.height);
                    }, 1000);
                }
            }
        },
        boxOnOff() {},
        createCanvas() {
            if (document.getElementsByTagName('canvas').length == 0) {
                this.canvas = window.faceapi.createCanvasFromMedia(this.$refs.webcamElement);
                this.$refs.webcamContainer.append(this.canvas);
                this.canvas.style = 'position: absolute;width: 100% !important;height: auto !important;top:0;left: 0; right:0;bottom:0;transform: scaleX(-1);z-index:1;';
            }
        },
        startDetection() {
            this.faceDetection = setInterval(async () => {
                // let start = new Date();

                this.displaySize = {width: this.$refs.webcamElement.scrollWidth, height: this.$refs.webcamElement.scrollHeight};
                window.faceapi.matchDimensions(this.canvas, this.displaySize);
                const detections = await window.faceapi.detectAllFaces(this.$refs.webcamElement, new window.faceapi.TinyFaceDetectorOptions());
                // .withFaceExpressions();
                const resizedDetections = window.faceapi.resizeResults(detections, this.displaySize);
                this.canvas.getContext('2d').clearRect(0, 0, this.canvas.width, this.canvas.height);
                window.faceapi.draw.drawDetections(this.canvas, resizedDetections);
                if (detections.length > 0) {
                    this.time += activeTime;
                    this.transferTime += activeTime;
                    let date = new Date(0);
                    date.setSeconds(this.time); // specify value for SECONDS here
                    this.timeString = date.toISOString().substring(11, 19);
                    if (this.transferTime > 4.9) {
                        //1분 마다 데이터 전송
                        axios.post(`${port}timer/member`,{category:this.webcamSwitch[0],second:5}).then((ret)=>console.log(ret)).catch((err)=>console.log(err));
                        this.transferTime = 0.0;
                        /* 
                여기에 store의 axois로 /timer/member/로 post 값을 보내주면 된다.(비동기로 보내기를 원합니다. 정확한 시간을 위해)
                category 는 this.webcamSwitch[0]
              */
                    }
                }

                // let last = new Date();
                // console.log(last - start);
            }, 300);
        }
    }
};
</script>
<style scoped>
.card_section_category {
    padding: 10px;
    /* margin-bottom: 1px; */
    width: 100%;
    height: 100px;
    border-style: solid;
    border-color: #eeeeee;
    border-radius: 30px;
    justify-items: center;
    align-items: center;
    /* border-width: thin; */
}
video {
    background: black;
    width: 100% !important;
    height: auto !important;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    border: 0px;
    z-index: 0;
}
h1 {
    background-color: azure;
}
.category {
    font-size: xx-large;
}
</style>

<!-- 
  <style>
  #webcam-container {
    padding: 0;
  }
  canvas {
    position: absolute;
    top: 0;
    left: 0;
  }
  video {
    background: black;
    width: 100% !important;
    height: auto !important;
    margin: 0;
    border: 0px;
  }
  #cameraFlip {
    width: 32px;
    height: 32px;
    margin-left: 10px;
    position: absolute;
    cursor: pointer;
    background-color: transparent;
    background-position: center center;
    background-repeat: no-repeat;
  }
  .loading {
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    z-index: 300000;
    border: white 1px;
    color: white;
    padding: 75px 26px;
    font-size: 22px;
    margin: auto;
    width: 200px;
    height: 200px;
  }
  .spinner-border {
    position: absolute;
    top: 0;
    left: 0;
    width: 200px;
    height: 200px;
    color: white;
    z-index: 300000;
    filter: alpha(opacity=80);
    -moz-opacity: 0.8;
    opacity: 0.8;
  }
  @media screen and (max-width: 575px) {
    .form-control {
      height: 40px;
      margin-bottom: 5px;
      padding: 15px 0px 15px 10px;
      border: 0px;
    }
  }
  @media screen and (min-width: 576px) {
    .form-control {
      padding-top: 15px;
      padding-bottom: 15px;
      height: 60px;
      margin-bottom: 5px;
    }
  }
  .camerasList {
    width: 90%;
  }
  .form-switch {
    display: inline-block;
    cursor: pointer;
    -webkit-tap-highlight-color: transparent;
  }
  .form-switch i {
    position: relative;
    display: inline-block;
    margin-right: 0.5rem;
    width: 46px;
    height: 26px;
    background-color: #e6e6e6;
    border-radius: 23px;
    vertical-align: text-bottom;
    transition: all 0.3s linear;
  }
  .form-switch i::before {
    content: "";
    position: absolute;
    left: 0;
    width: 42px;
    height: 22px;
    background-color: #fff;
    border-radius: 11px;
    transform: translate3d(2px, 2px, 0) scale3d(1, 1, 1);
    transition: all 0.25s linear;
  }
  .form-switch i::after {
    content: "";
    position: absolute;
    left: 0;
    width: 22px;
    height: 22px;
    background-color: #fff;
    border-radius: 11px;
    box-shadow: 0 2px 2px rgba(0, 0, 0, 0.25);
    transform: translate3d(2px, 2px, 0);
    transition: all 0.2s ease-in-out;
  }
  .form-switch:active i::after {
    width: 28px;
    transform: translate3d(2px, 2px, 0);
  }
  .form-switch:active input:checked + i::after {
    transform: translate3d(16px, 2px, 0);
  }
  .form-switch input {
    display: none;
  }
  .form-switch input:checked + i {
    background-color: #4bd763;
  }
  .form-switch input:checked + i::before {
    transform: translate3d(18px, 2px, 0) scale3d(0, 0, 0);
  }
  .form-switch input:checked + i::after {
    transform: translate3d(22px, 2px, 0);
  }
  .form-switch input:disabled + i {
    background-color: #eeeeee;
    cursor: not-allowed;
  }
  .form-switch input:disabled + i::after {
    box-shadow: 0 2px 2px rgba(0, 0, 0, 0.1);
  }
  .disabled {
    color: #aaa;
    cursor: not-allowed;
  }
  #errorMsg {
    position: absolute;
    top: 0;
    left: 0;
    padding: 20px;
    z-index: 999999;
  }
  </style> -->
