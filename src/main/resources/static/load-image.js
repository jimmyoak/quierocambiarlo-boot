!function(c){"use strict";var t=c.URL||c.webkitURL;function f(e){return!!t&&t.createObjectURL(e)}function i(e){return!!t&&t.revokeObjectURL(e)}function u(e,t){!e||"blob:"!==e.slice(0,5)||t&&t.noRevoke||i(e)}function d(e,t,i,a){if(!c.FileReader)return!1;var n=new FileReader;n.onload=function(){t.call(n,this.result)},i&&(n.onabort=n.onerror=function(){i.call(n,this.error)});var r=n[a||"readAsDataURL"];return r?(r.call(n,e),n):void 0}function g(e,t){return Object.prototype.toString.call(t)==="[object "+e+"]"}function m(s,e,l){function t(i,a){var n,r=document.createElement("img");function o(e,t){i!==a?e instanceof Error?a(e):((t=t||{}).image=e,i(t)):i&&i(e,t)}function e(e,t){t&&c.console&&console.log(t),e&&g("Blob",e)?n=f(s=e):(n=s,l&&l.crossOrigin&&(r.crossOrigin=l.crossOrigin)),r.src=n}return r.onerror=function(e){u(n,l),a&&a.call(r,e)},r.onload=function(){u(n,l);var e={originalWidth:r.naturalWidth||r.width,originalHeight:r.naturalHeight||r.height};try{m.transform(r,l,o,s,e)}catch(t){a&&a(t)}},"string"==typeof s?(m.requiresMetaData(l)?m.fetchBlob(s,e,l):e(),r):g("Blob",s)||g("File",s)?(n=f(s))?(r.src=n,r):d(s,function(e){r.src=e},a):void 0}return c.Promise&&"function"!=typeof e?(l=e,new Promise(t)):t(e,e)}m.requiresMetaData=function(e){return e&&e.meta},m.fetchBlob=function(e,t){t()},m.transform=function(e,t,i,a,n){i(e,n)},m.global=c,m.readFile=d,m.isInstanceOf=g,m.createObjectURL=f,m.revokeObjectURL=i,"function"==typeof define&&define.amd?define(function(){return m}):"object"==typeof module&&module.exports?module.exports=m:c.loadImage=m}("undefined"!=typeof window&&window||this),function(e){"use strict";"function"==typeof define&&define.amd?define(["./load-image"],e):"object"==typeof module&&module.exports?e(require("./load-image")):e(window.loadImage)}(function(E){"use strict";var r=E.transform;E.createCanvas=function(e,t,i){if(i&&E.global.OffscreenCanvas)return new OffscreenCanvas(e,t);var a=document.createElement("canvas");return a.width=e,a.height=t,a},E.transform=function(e,t,i,a,n){r.call(E,E.scale(e,t,n),t,i,a,n)},E.transformCoordinates=function(){},E.getTransformedOptions=function(e,t){var i,a,n,r,o=t.aspectRatio;if(!o)return t;for(a in i={},t)Object.prototype.hasOwnProperty.call(t,a)&&(i[a]=t[a]);return i.crop=!0,o<(n=e.naturalWidth||e.width)/(r=e.naturalHeight||e.height)?(i.maxWidth=r*o,i.maxHeight=r):(i.maxWidth=n,i.maxHeight=n/o),i},E.drawImage=function(e,t,i,a,n,r,o,s,l){var c=t.getContext("2d");return!1===l.imageSmoothingEnabled?(c.msImageSmoothingEnabled=!1,c.imageSmoothingEnabled=!1):l.imageSmoothingQuality&&(c.imageSmoothingQuality=l.imageSmoothingQuality),c.drawImage(e,i,a,n,r,0,0,o,s),c},E.requiresCanvas=function(e){return e.canvas||e.crop||!!e.aspectRatio},E.scale=function(e,t,i){t=t||{},i=i||{};var a,n,r,o,s,l,c,f,u,d,g,m,h=e.getContext||E.requiresCanvas(t)&&!!E.global.HTMLCanvasElement,p=e.naturalWidth||e.width,A=e.naturalHeight||e.height,b=p,y=A;function S(){var e=Math.max((r||b)/b,(o||y)/y);1<e&&(b*=e,y*=e)}function v(){var e=Math.min((a||b)/b,(n||y)/y);e<1&&(b*=e,y*=e)}if(h&&(c=(t=E.getTransformedOptions(e,t,i)).left||0,f=t.top||0,t.sourceWidth?(s=t.sourceWidth,t.right!==undefined&&t.left===undefined&&(c=p-s-t.right)):s=p-c-(t.right||0),t.sourceHeight?(l=t.sourceHeight,t.bottom!==undefined&&t.top===undefined&&(f=A-l-t.bottom)):l=A-f-(t.bottom||0),b=s,y=l),a=t.maxWidth,n=t.maxHeight,r=t.minWidth,o=t.minHeight,h&&a&&n&&t.crop?(g=s/l-(b=a)/(y=n))<0?(l=n*s/a,t.top===undefined&&t.bottom===undefined&&(f=(A-l)/2)):0<g&&(s=a*l/n,t.left===undefined&&t.right===undefined&&(c=(p-s)/2)):((t.contain||t.cover)&&(r=a=a||r,o=n=n||o),t.cover?(v(),S()):(S(),v())),h){if(1<(u=t.pixelRatio)&&(!e.style.width||Math.floor(parseFloat(e.style.width,10))!==Math.floor(p/u))&&(b*=u,y*=u),E.orientationCropBug&&!e.getContext&&(c||f||s!==p||l!==A)&&(g=e,e=E.createCanvas(p,A,!0),E.drawImage(g,e,0,0,p,A,p,A,t)),0<(d=t.downsamplingRatio)&&d<1&&b<s&&y<l)for(;b<s*d;)m=E.createCanvas(s*d,l*d,!0),E.drawImage(e,m,c,f,s,l,m.width,m.height,t),f=c=0,s=m.width,l=m.height,e=m;return m=E.createCanvas(b,y),E.transformCoordinates(m,t,i),1<u&&(m.style.width=m.width/u+"px"),E.drawImage(e,m,c,f,s,l,b,y,t).setTransform(1,0,0,1,0,0),m}return e.width=b,e.height=y,e}}),function(e){"use strict";"function"==typeof define&&define.amd?define(["./load-image"],e):"object"==typeof module&&module.exports?e(require("./load-image")):e(window.loadImage)}(function(o){"use strict";var s=o.global,l=o.transform,a=s.Blob&&(Blob.prototype.slice||Blob.prototype.webkitSlice||Blob.prototype.mozSlice),m=s.ArrayBuffer&&ArrayBuffer.prototype.slice||function(e,t){t=t||this.byteLength-e;var i=new Uint8Array(this,e,t),a=new Uint8Array(t);return a.set(i),a.buffer},h={jpeg:{65505:[],65517:[]}};function c(t,e,u,d){var g=this;function i(c,f){if(!(s.DataView&&a&&t&&12<=t.size&&"image/jpeg"===t.type))return c(d);var e=u.maxMetaDataSize||262144;o.readFile(a.call(t,0,e),function(e){var t=new DataView(e);if(65496!==t.getUint16(0))return f(new Error("Invalid JPEG file: Missing JPEG marker."));for(var i,a,n,r,o=2,s=t.byteLength-4,l=o;o<s&&(65504<=(i=t.getUint16(o))&&i<=65519||65534===i);){if(o+(a=t.getUint16(o+2)+2)>t.byteLength){console.log("Invalid JPEG metadata: Invalid segment size.");break}if((n=h.jpeg[i])&&!u.disableMetaDataParsers)for(r=0;r<n.length;r+=1)n[r].call(g,t,o,a,d,u);l=o+=a}!u.disableImageHead&&6<l&&(d.imageHead=m.call(e,0,l)),c(d)},f,"readAsArrayBuffer")||c(d)}return u=u||{},s.Promise&&"function"!=typeof e?(d=u=e||{},new Promise(i)):(d=d||{},i(e,e))}function n(e,t,i){return e&&t&&i?new Blob([i,a.call(e,t.byteLength)],{type:"image/jpeg"}):null}o.transform=function(t,i,a,n,r){o.requiresMetaData(i)?c(n,function(e){e!==r&&(s.console&&console.log(e),e=r),l.call(o,t,i,a,n,e)},i,r=r||{}):l.apply(o,arguments)},o.blobSlice=a,o.bufferSlice=m,o.replaceHead=function(t,i,a){var e={maxMetaDataSize:256,disableMetaDataParsers:!0};if(!a&&s.Promise)return c(t,e).then(function(e){return n(t,e.imageHead,i)});c(t,function(e){a(n(t,e.imageHead,i))},e)},o.parseMetaData=c,o.metaDataParsers=h}),function(e){"use strict";"function"==typeof define&&define.amd?define(["./load-image"],e):"object"==typeof module&&module.exports?e(require("./load-image")):e(window.loadImage)}(function(e){"use strict";var r=e.global;r.fetch&&r.Request&&r.Response&&r.Response.prototype.blob?e.fetchBlob=function(e,t,i){function a(e){return e.blob()}if(r.Promise&&"function"!=typeof t)return fetch(new Request(e,t)).then(a);fetch(new Request(e,i)).then(a).then(t)["catch"](function(e){t(null,e)})}:r.XMLHttpRequest&&""===(new XMLHttpRequest).responseType&&(e.fetchBlob=function(e,t,n){function i(t,i){n=n||{};var a=new XMLHttpRequest;a.open(n.method||"GET",e),n.headers&&Object.keys(n.headers).forEach(function(e){a.setRequestHeader(e,n.headers[e])}),a.withCredentials="include"===n.credentials,a.responseType="blob",a.onload=function(){t(a.response)},a.onerror=a.onabort=a.ontimeout=function(e){t===i?i(null,e):i(e)},a.send(n.body)}return r.Promise&&"function"!=typeof t?(n=t,new Promise(i)):i(t,t)})}),function(e){"use strict";"function"==typeof define&&define.amd?define(["./load-image","./load-image-scale","./load-image-meta"],e):"object"==typeof module&&module.exports?e(require("./load-image"),require("./load-image-scale"),require("./load-image-meta")):e(window.loadImage)}(function(h){"use strict";var t,i,n=h.transform,a=h.requiresCanvas,r=h.requiresMetaData,f=h.transformCoordinates,p=h.getTransformedOptions;function o(e,t){var i=e&&e.orientation;return!0===i&&!h.orientation||1===i&&h.orientation||(!t||h.orientation)&&1<i&&i<9}function A(e,t){return e!==t&&(1===e&&1<t&&t<9||1<e&&e<9)}function b(e,t){if(1<t&&t<9)switch(e){case 2:case 4:return 4<t;case 5:case 7:return t%2==0;case 6:case 8:return 2===t||4===t||5===t||7===t}}(t=h).global.document&&((i=document.createElement("img")).onload=function(){var e;t.orientation=2===i.width&&3===i.height,t.orientation&&((e=t.createCanvas(1,1,!0).getContext("2d")).drawImage(i,1,1,1,1,0,0,1,1),t.orientationCropBug="255,255,255,255"!==e.getImageData(0,0,1,1).data.toString())},i.src="data:image/jpeg;base64,/9j/4QAiRXhpZgAATU0AKgAAAAgAAQESAAMAAAABAAYAAAAAAAD/2wCEAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAf/AABEIAAIAAwMBEQACEQEDEQH/xABRAAEAAAAAAAAAAAAAAAAAAAAKEAEBAQADAQEAAAAAAAAAAAAGBQQDCAkCBwEBAAAAAAAAAAAAAAAAAAAAABEBAAAAAAAAAAAAAAAAAAAAAP/aAAwDAQACEQMRAD8AG8T9NfSMEVMhQvoP3fFiRZ+MTHDifa/95OFSZU5OzRzxkyejv8ciEfhSceSXGjS8eSdLnZc2HDm4M3BxcXwH/9k="),h.requiresCanvas=function(e){return o(e)||a.call(h,e)},h.requiresMetaData=function(e){return o(e,!0)||r.call(h,e)},h.transform=function(e,t,r,i,a){n.call(h,e,t,function(e,t){var i,a,n;!t||4<(i=h.orientation&&t.exif&&t.exif.get("Orientation"))&&i<9&&(a=t.originalWidth,n=t.originalHeight,t.originalWidth=n,t.originalHeight=a),r(e,t)},i,a)},h.getTransformedOptions=function(e,t,i){var a=p.call(h,e,t),n=i.exif&&i.exif.get("Orientation"),r=a.orientation,o=h.orientation&&n;if(!0===r&&(r=n),!A(r,o))return a;var s,l,c=a.top,f=a.right,u=a.bottom,d=a.left,g={};for(var m in a)Object.prototype.hasOwnProperty.call(a,m)&&(g[m]=a[m]);if((4<(g.orientation=r)&&!(4<o)||r<5&&4<o)&&(g.maxWidth=a.maxHeight,g.maxHeight=a.maxWidth,g.minWidth=a.minHeight,g.minHeight=a.minWidth,g.sourceWidth=a.sourceHeight,g.sourceHeight=a.sourceWidth),1<o){switch(o){case 2:f=a.left,d=a.right;break;case 3:c=a.bottom,f=a.left,u=a.top,d=a.right;break;case 4:c=a.bottom,u=a.top;break;case 5:c=a.left,f=a.bottom,u=a.right,d=a.top;break;case 6:c=a.left,f=a.top,u=a.right,d=a.bottom;break;case 7:c=a.right,f=a.top,u=a.left,d=a.bottom;break;case 8:c=a.right,f=a.bottom,u=a.left,d=a.top}b(r,o)&&(s=c,l=f,c=u,f=d,u=s,d=l)}switch(g.top=c,g.right=f,g.bottom=u,g.left=d,r){case 2:g.right=d,g.left=f;break;case 3:g.top=u,g.right=d,g.bottom=c,g.left=f;break;case 4:g.top=u,g.bottom=c;break;case 5:g.top=d,g.right=u,g.bottom=f,g.left=c;break;case 6:g.top=f,g.right=u,g.bottom=d,g.left=c;break;case 7:g.top=f,g.right=c,g.bottom=d,g.left=u;break;case 8:g.top=d,g.right=c,g.bottom=f,g.left=u}return g},h.transformCoordinates=function(e,t,i){f.call(h,e,t,i);var a=t.orientation,n=h.orientation&&i.exif&&i.exif.get("Orientation");if(A(a,n)){var r=e.getContext("2d"),o=e.width,s=e.height,l=o,c=s;switch((4<a&&!(4<n)||a<5&&4<n)&&(e.width=s,e.height=o),4<a&&(l=s,c=o),n){case 2:r.translate(l,0),r.scale(-1,1);break;case 3:r.translate(l,c),r.rotate(Math.PI);break;case 4:r.translate(0,c),r.scale(1,-1);break;case 5:r.rotate(-.5*Math.PI),r.scale(-1,1);break;case 6:r.rotate(-.5*Math.PI),r.translate(-l,0);break;case 7:r.rotate(-.5*Math.PI),r.translate(-l,c),r.scale(1,-1);break;case 8:r.rotate(.5*Math.PI),r.translate(0,-c)}switch(b(a,n)&&(r.translate(l,c),r.rotate(Math.PI)),a){case 2:r.translate(o,0),r.scale(-1,1);break;case 3:r.translate(o,s),r.rotate(Math.PI);break;case 4:r.translate(0,s),r.scale(1,-1);break;case 5:r.rotate(.5*Math.PI),r.scale(1,-1);break;case 6:r.rotate(.5*Math.PI),r.translate(0,-s);break;case 7:r.rotate(.5*Math.PI),r.translate(o,-s),r.scale(-1,1);break;case 8:r.rotate(-.5*Math.PI),r.translate(-o,0)}}}}),function(e){"use strict";"function"==typeof define&&define.amd?define(["./load-image","./load-image-meta"],e):"object"==typeof module&&module.exports?e(require("./load-image"),require("./load-image-meta")):e(window.loadImage)}(function(r){"use strict";function h(e){e&&(Object.defineProperty(this,"map",{value:this.ifds[e].map}),Object.defineProperty(this,"tags",{value:this.tags&&this.tags[e]||{}}))}h.prototype.ifds={ifd1:{name:"Thumbnail",map:h.prototype.map={Orientation:274,Thumbnail:"ifd1",Blob:513,Exif:34665,GPSInfo:34853,Interoperability:40965}},34665:{name:"Exif",map:{}},34853:{name:"GPSInfo",map:{}},40965:{name:"Interoperability",map:{}}},h.prototype.get=function(e){return this[e]||this[this.map[e]]};var m={1:{getValue:function(e,t){return e.getUint8(t)},size:1},2:{getValue:function(e,t){return String.fromCharCode(e.getUint8(t))},size:1,ascii:!0},3:{getValue:function(e,t,i){return e.getUint16(t,i)},size:2},4:{getValue:function(e,t,i){return e.getUint32(t,i)},size:4},5:{getValue:function(e,t,i){return e.getUint32(t,i)/e.getUint32(t+4,i)},size:8},9:{getValue:function(e,t,i){return e.getInt32(t,i)},size:4},10:{getValue:function(e,t,i){return e.getInt32(t,i)/e.getInt32(t+4,i)},size:8}};function p(e,t,i){return(!e||e[i])&&(!t||!0!==t[i])}function A(e,t,i,a,n,r,o,s){var l,c,f,u,d,g;if(i+6>e.byteLength)console.log("Invalid Exif data: Invalid directory offset.");else{if(!((c=i+2+12*(l=e.getUint16(i,a)))+4>e.byteLength)){for(f=0;f<l;f+=1)u=i+2+12*f,p(o,s,d=e.getUint16(u,a))&&(g=function(e,t,i,a,n,r){var o,s,l,c,f,u,d=m[a];if(d){if(!((s=4<(o=d.size*n)?t+e.getUint32(i+8,r):i+8)+o>e.byteLength)){if(1===n)return d.getValue(e,s,r);for(l=[],c=0;c<n;c+=1)l[c]=d.getValue(e,s+c*d.size,r);if(d.ascii){for(f="",c=0;c<l.length&&"\0"!==(u=l[c]);c+=1)f+=u;return f}return l}console.log("Invalid Exif data: Invalid data offset.")}else console.log("Invalid Exif data: Invalid tag type.")}(e,t,u,e.getUint16(u+2,a),e.getUint32(u+4,a),a),n[d]=g,r&&(r[d]=u));return e.getUint32(c,a)}console.log("Invalid Exif data: Invalid directory size.")}}m[7]=m[1],r.parseExifData=function(c,e,t,f,i){if(!i.disableExif){var u,a,n,d=i.includeExifTags,g=i.excludeExifTags||{34665:{37500:!0}},m=e+10;if(1165519206===c.getUint32(e+4))if(m+8>c.byteLength)console.log("Invalid Exif data: Invalid segment size.");else if(0===c.getUint16(e+8)){switch(c.getUint16(m)){case 18761:u=!0;break;case 19789:u=!1;break;default:return void console.log("Invalid Exif data: Invalid byte alignment marker.")}42===c.getUint16(m+2,u)?(a=c.getUint32(m+4,u),f.exif=new h,i.disableExifOffsets||(f.exifOffsets=new h,f.exifTiffOffset=m,f.exifLittleEndian=u),(a=A(c,m,m+a,u,f.exif,f.exifOffsets,d,g))&&p(d,g,"ifd1")&&(f.exif.ifd1=a,f.exifOffsets&&(f.exifOffsets.ifd1=m+a)),Object.keys(f.exif.ifds).forEach(function(e){var t,i,a,n,r,o,s,l;i=e,a=c,n=m,r=u,o=d,s=g,(l=(t=f).exif[i])&&(t.exif[i]=new h(i),t.exifOffsets&&(t.exifOffsets[i]=new h(i)),A(a,n,n+l,r,t.exif[i],t.exifOffsets&&t.exifOffsets[i],o&&o[i],s&&s[i]))}),(n=f.exif.ifd1)&&n[513]&&(n[513]=function(e,t,i){if(i){if(!(t+i>e.byteLength))return new Blob([r.bufferSlice.call(e.buffer,t,t+i)],{type:"image/jpeg"});console.log("Invalid Exif data: Invalid thumbnail data.")}}(c,m+n[513],n[514]))):console.log("Invalid Exif data: Missing TIFF marker.")}else console.log("Invalid Exif data: Missing byte alignment offset.")}},r.metaDataParsers.jpeg[65505].push(r.parseExifData),r.exifWriters={274:function(e,t,i){var a=t.exifOffsets[274];return a&&new DataView(e,a+8,2).setUint16(0,i,t.exifLittleEndian),e}},r.writeExifData=function(e,t,i,a){return r.exifWriters[t.exif.map[i]](e,t,a)},r.ExifMap=h}),function(e){"use strict";"function"==typeof define&&define.amd?define(["./load-image","./load-image-exif"],e):"object"==typeof module&&module.exports?e(require("./load-image"),require("./load-image-exif")):e(window.loadImage)}(function(e){"use strict";var n=e.ExifMap.prototype;n.tags={256:"ImageWidth",257:"ImageHeight",258:"BitsPerSample",259:"Compression",262:"PhotometricInterpretation",274:"Orientation",277:"SamplesPerPixel",284:"PlanarConfiguration",530:"YCbCrSubSampling",531:"YCbCrPositioning",282:"XResolution",283:"YResolution",296:"ResolutionUnit",273:"StripOffsets",278:"RowsPerStrip",279:"StripByteCounts",513:"JPEGInterchangeFormat",514:"JPEGInterchangeFormatLength",301:"TransferFunction",318:"WhitePoint",319:"PrimaryChromaticities",529:"YCbCrCoefficients",532:"ReferenceBlackWhite",306:"DateTime",270:"ImageDescription",271:"Make",272:"Model",305:"Software",315:"Artist",33432:"Copyright",34665:{36864:"ExifVersion",40960:"FlashpixVersion",40961:"ColorSpace",40962:"PixelXDimension",40963:"PixelYDimension",42240:"Gamma",37121:"ComponentsConfiguration",37122:"CompressedBitsPerPixel",37500:"MakerNote",37510:"UserComment",40964:"RelatedSoundFile",36867:"DateTimeOriginal",36868:"DateTimeDigitized",36880:"OffsetTime",36881:"OffsetTimeOriginal",36882:"OffsetTimeDigitized",37520:"SubSecTime",37521:"SubSecTimeOriginal",37522:"SubSecTimeDigitized",33434:"ExposureTime",33437:"FNumber",34850:"ExposureProgram",34852:"SpectralSensitivity",34855:"PhotographicSensitivity",34856:"OECF",34864:"SensitivityType",34865:"StandardOutputSensitivity",34866:"RecommendedExposureIndex",34867:"ISOSpeed",34868:"ISOSpeedLatitudeyyy",34869:"ISOSpeedLatitudezzz",37377:"ShutterSpeedValue",37378:"ApertureValue",37379:"BrightnessValue",37380:"ExposureBias",37381:"MaxApertureValue",37382:"SubjectDistance",37383:"MeteringMode",37384:"LightSource",37385:"Flash",37396:"SubjectArea",37386:"FocalLength",41483:"FlashEnergy",41484:"SpatialFrequencyResponse",41486:"FocalPlaneXResolution",41487:"FocalPlaneYResolution",41488:"FocalPlaneResolutionUnit",41492:"SubjectLocation",41493:"ExposureIndex",41495:"SensingMethod",41728:"FileSource",41729:"SceneType",41730:"CFAPattern",41985:"CustomRendered",41986:"ExposureMode",41987:"WhiteBalance",41988:"DigitalZoomRatio",41989:"FocalLengthIn35mmFilm",41990:"SceneCaptureType",41991:"GainControl",41992:"Contrast",41993:"Saturation",41994:"Sharpness",41995:"DeviceSettingDescription",41996:"SubjectDistanceRange",42016:"ImageUniqueID",42032:"CameraOwnerName",42033:"BodySerialNumber",42034:"LensSpecification",42035:"LensMake",42036:"LensModel",42037:"LensSerialNumber"},34853:{0:"GPSVersionID",1:"GPSLatitudeRef",2:"GPSLatitude",3:"GPSLongitudeRef",4:"GPSLongitude",5:"GPSAltitudeRef",6:"GPSAltitude",7:"GPSTimeStamp",8:"GPSSatellites",9:"GPSStatus",10:"GPSMeasureMode",11:"GPSDOP",12:"GPSSpeedRef",13:"GPSSpeed",14:"GPSTrackRef",15:"GPSTrack",16:"GPSImgDirectionRef",17:"GPSImgDirection",18:"GPSMapDatum",19:"GPSDestLatitudeRef",20:"GPSDestLatitude",21:"GPSDestLongitudeRef",22:"GPSDestLongitude",23:"GPSDestBearingRef",24:"GPSDestBearing",25:"GPSDestDistanceRef",26:"GPSDestDistance",27:"GPSProcessingMethod",28:"GPSAreaInformation",29:"GPSDateStamp",30:"GPSDifferential",31:"GPSHPositioningError"},40965:{1:"InteroperabilityIndex"}},n.tags.ifd1=n.tags,n.stringValues={ExposureProgram:{0:"Undefined",1:"Manual",2:"Normal program",3:"Aperture priority",4:"Shutter priority",5:"Creative program",6:"Action program",7:"Portrait mode",8:"Landscape mode"},MeteringMode:{0:"Unknown",1:"Average",2:"CenterWeightedAverage",3:"Spot",4:"MultiSpot",5:"Pattern",6:"Partial",255:"Other"},LightSource:{0:"Unknown",1:"Daylight",2:"Fluorescent",3:"Tungsten (incandescent light)",4:"Flash",9:"Fine weather",10:"Cloudy weather",11:"Shade",12:"Daylight fluorescent (D 5700 - 7100K)",13:"Day white fluorescent (N 4600 - 5400K)",14:"Cool white fluorescent (W 3900 - 4500K)",15:"White fluorescent (WW 3200 - 3700K)",17:"Standard light A",18:"Standard light B",19:"Standard light C",20:"D55",21:"D65",22:"D75",23:"D50",24:"ISO studio tungsten",255:"Other"},Flash:{0:"Flash did not fire",1:"Flash fired",5:"Strobe return light not detected",7:"Strobe return light detected",9:"Flash fired, compulsory flash mode",13:"Flash fired, compulsory flash mode, return light not detected",15:"Flash fired, compulsory flash mode, return light detected",16:"Flash did not fire, compulsory flash mode",24:"Flash did not fire, auto mode",25:"Flash fired, auto mode",29:"Flash fired, auto mode, return light not detected",31:"Flash fired, auto mode, return light detected",32:"No flash function",65:"Flash fired, red-eye reduction mode",69:"Flash fired, red-eye reduction mode, return light not detected",71:"Flash fired, red-eye reduction mode, return light detected",73:"Flash fired, compulsory flash mode, red-eye reduction mode",77:"Flash fired, compulsory flash mode, red-eye reduction mode, return light not detected",79:"Flash fired, compulsory flash mode, red-eye reduction mode, return light detected",89:"Flash fired, auto mode, red-eye reduction mode",93:"Flash fired, auto mode, return light not detected, red-eye reduction mode",95:"Flash fired, auto mode, return light detected, red-eye reduction mode"},SensingMethod:{1:"Undefined",2:"One-chip color area sensor",3:"Two-chip color area sensor",4:"Three-chip color area sensor",5:"Color sequential area sensor",7:"Trilinear sensor",8:"Color sequential linear sensor"},SceneCaptureType:{0:"Standard",1:"Landscape",2:"Portrait",3:"Night scene"},SceneType:{1:"Directly photographed"},CustomRendered:{0:"Normal process",1:"Custom process"},WhiteBalance:{0:"Auto white balance",1:"Manual white balance"},GainControl:{0:"None",1:"Low gain up",2:"High gain up",3:"Low gain down",4:"High gain down"},Contrast:{0:"Normal",1:"Soft",2:"Hard"},Saturation:{0:"Normal",1:"Low saturation",2:"High saturation"},Sharpness:{0:"Normal",1:"Soft",2:"Hard"},SubjectDistanceRange:{0:"Unknown",1:"Macro",2:"Close view",3:"Distant view"},FileSource:{3:"DSC"},ComponentsConfiguration:{0:"",1:"Y",2:"Cb",3:"Cr",4:"R",5:"G",6:"B"},Orientation:{1:"Original",2:"Horizontal flip",3:"Rotate 180° CCW",4:"Vertical flip",5:"Vertical flip + Rotate 90° CW",6:"Rotate 90° CW",7:"Horizontal flip + Rotate 90° CW",8:"Rotate 90° CCW"}},n.getText=function(e){var t=this.get(e);switch(e){case"LightSource":case"Flash":case"MeteringMode":case"ExposureProgram":case"SensingMethod":case"SceneCaptureType":case"SceneType":case"CustomRendered":case"WhiteBalance":case"GainControl":case"Contrast":case"Saturation":case"Sharpness":case"SubjectDistanceRange":case"FileSource":case"Orientation":return this.stringValues[e][t];case"ExifVersion":case"FlashpixVersion":if(!t)return;return String.fromCharCode(t[0],t[1],t[2],t[3]);case"ComponentsConfiguration":if(!t)return;return this.stringValues[e][t[0]]+this.stringValues[e][t[1]]+this.stringValues[e][t[2]]+this.stringValues[e][t[3]];case"GPSVersionID":if(!t)return;return t[0]+"."+t[1]+"."+t[2]+"."+t[3]}return String(t)},n.getAll=function(){var e,t,i,a={};for(e in this)Object.prototype.hasOwnProperty.call(this,e)&&((t=this[e])&&t.getAll?a[this.ifds[e].name]=t.getAll():(i=this.tags[e])&&(a[i]=this.getText(i)));return a},n.getName=function(e){var t=this.tags[e];return"object"==typeof t?this.ifds[e].name:t},function(){var e,t,i,a=n.tags;for(e in a)if(Object.prototype.hasOwnProperty.call(a,e))if(t=n.ifds[e])for(e in i=a[e])Object.prototype.hasOwnProperty.call(i,e)&&(t.map[i[e]]=Number(e));else n.map[a[e]]=Number(e)}()}),function(e){"use strict";"function"==typeof define&&define.amd?define(["./load-image","./load-image-meta"],e):"object"==typeof module&&module.exports?e(require("./load-image"),require("./load-image-meta")):e(window.loadImage)}(function(e){"use strict";function g(){}function m(e,t,i,a,n){return"binary"===t.types[e]?new Blob([i.buffer.slice(a,a+n)]):"Uint16"===t.types[e]?i.getUint16(a):function(e,t,i){for(var a="",n=t+i,r=t;r<n;r+=1)a+=String.fromCharCode(e.getUint8(r));return a}(i,a,n)}function h(e,t,i,a,n,r){for(var o,s,l,c,f,u=t+i,d=t;d<u;)28===e.getUint8(d)&&2===e.getUint8(d+1)&&(l=e.getUint8(d+2),n&&!n[l]||r&&r[l]||(s=e.getInt16(d+3),o=m(l,a.iptc,e,d+5,s),a.iptc[l]=(c=a.iptc[l],f=o,c===undefined?f:c instanceof Array?(c.push(f),c):[c,f]),a.iptcOffsets&&(a.iptcOffsets[l]=d))),d+=1}g.prototype.map={ObjectName:5},g.prototype.types={0:"Uint16",200:"Uint16",201:"Uint16",202:"binary"},g.prototype.get=function(e){return this[e]||this[this.map[e]]},e.parseIptcData=function(e,t,i,a,n){if(!n.disableIptc)for(var r,o,s,l,c=t+i;t+8<c;){if(l=t,943868237===(s=e).getUint32(l)&&1028===s.getUint16(l+4)){var f=(r=t,o=void 0,(o=e.getUint8(r+7))%2!=0&&(o+=1),0===o&&(o=4),o),u=t+8+f;if(c<u){console.log("Invalid IPTC data: Invalid segment offset.");break}var d=e.getUint16(t+6+f);if(c<t+d){console.log("Invalid IPTC data: Invalid segment size.");break}return a.iptc=new g,n.disableIptcOffsets||(a.iptcOffsets=new g),void h(e,u,d,a,n.includeIptcTags,n.excludeIptcTags||{202:!0})}t+=1}},e.metaDataParsers.jpeg[65517].push(e.parseIptcData),e.IptcMap=g}),function(e){"use strict";"function"==typeof define&&define.amd?define(["./load-image","./load-image-iptc"],e):"object"==typeof module&&module.exports?e(require("./load-image"),require("./load-image-iptc")):e(window.loadImage)}(function(e){"use strict";var a=e.IptcMap.prototype;a.tags={0:"ApplicationRecordVersion",3:"ObjectTypeReference",4:"ObjectAttributeReference",5:"ObjectName",7:"EditStatus",8:"EditorialUpdate",10:"Urgency",12:"SubjectReference",15:"Category",20:"SupplementalCategories",22:"FixtureIdentifier",25:"Keywords",26:"ContentLocationCode",27:"ContentLocationName",30:"ReleaseDate",35:"ReleaseTime",37:"ExpirationDate",38:"ExpirationTime",40:"SpecialInstructions",42:"ActionAdvised",45:"ReferenceService",47:"ReferenceDate",50:"ReferenceNumber",55:"DateCreated",60:"TimeCreated",62:"DigitalCreationDate",63:"DigitalCreationTime",65:"OriginatingProgram",70:"ProgramVersion",75:"ObjectCycle",80:"Byline",85:"BylineTitle",90:"City",92:"Sublocation",95:"State",100:"CountryCode",101:"Country",103:"OriginalTransmissionReference",105:"Headline",110:"Credit",115:"Source",116:"CopyrightNotice",118:"Contact",120:"Caption",121:"LocalCaption",122:"Writer",125:"RasterizedCaption",130:"ImageType",131:"ImageOrientation",135:"LanguageIdentifier",150:"AudioType",151:"AudioSamplingRate",152:"AudioSamplingResolution",153:"AudioDuration",154:"AudioOutcue",184:"JobID",185:"MasterDocumentID",186:"ShortDocumentID",187:"UniqueDocumentID",188:"OwnerID",200:"ObjectPreviewFileFormat",201:"ObjectPreviewFileVersion",202:"ObjectPreviewData",221:"Prefs",225:"ClassifyState",228:"SimilarityIndex",230:"DocumentNotes",231:"DocumentHistory",232:"ExifCameraInfo",255:"CatalogSets"},a.stringValues={10:{0:"0 (reserved)",1:"1 (most urgent)",2:"2",3:"3",4:"4",5:"5 (normal urgency)",6:"6",7:"7",8:"8 (least urgent)",9:"9 (user-defined priority)"},75:{a:"Morning",b:"Both Morning and Evening",p:"Evening"},131:{L:"Landscape",P:"Portrait",S:"Square"}},a.getText=function(e){var t=this.get(e),i=this.map[e],a=this.stringValues[i];return a?a[t]:String(t)},a.getAll=function(){var e,t,i={};for(e in this)Object.prototype.hasOwnProperty.call(this,e)&&(t=this.tags[e])&&(i[t]=this.getText(t));return i},a.getName=function(e){return this.tags[e]},function(){var e,t=a.tags,i=a.map||{};for(e in t)Object.prototype.hasOwnProperty.call(t,e)&&(i[t[e]]=Number(e))}()});
