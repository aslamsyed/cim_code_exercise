# cim_code_exercise
Ad Campaign Cim code exercise


REST End Points:
Post Ad Campaign API 
http://localhost:8080/ad
Method: GET
Request Body:
{
"partner_id" : "100",
"ad_content": "Xfinity Home Security Advertisement",
"duration" : 360000
}
Response:
{
  "message": "Ad created successfully",
  "status": "1",
  "ad": {
    "id": "589013f2cfd7422b5ebebaf8",
    "partner_id": "100",
    "duration": 360000,
    "ad_content": "Xfinity Home Security Advertisement",
    "sessionToken": 1485837298256,
    "status": null
  }
}

