from fastapi import FastAPI
from pydantic import BaseModel
from mangum import Mangum
from app.model.model1 import predict_pipeline


app = FastAPI()
handler = Mangum(app)

class ImgIn(BaseModel):
    url: str


class PredictionOut(BaseModel):
    prediction: float


@app.get("/")
def home():
    return {"health_check": "OK"}


@app.post("/predict", response_model=PredictionOut)
def predict(payload: ImgIn):
    prediction = predict_pipeline(payload.url)
    return {"prediction": prediction}