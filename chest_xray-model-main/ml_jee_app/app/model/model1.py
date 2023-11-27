import tensorflow as tf
from pathlib import Path
from PIL import Image
import numpy as np
import requests
from io import BytesIO
image_size = 150


BASE_DIR = Path(__file__).resolve(strict=True).parent

model = tf.keras.models.load_model(f"{BASE_DIR}/chest_xray-0.1.0.h5")


def predict_pipeline(image_url):
    try:
        # Download the image from the URL
        response = requests.get(image_url)
        response.raise_for_status()

        # Open the downloaded image using PIL
        with Image.open(BytesIO(response.content)) as img:
            # Resize the image
            img = img.resize((150, 150))
            img = img.convert('L')
            # Convert the PIL image to a NumPy array and normalize it
            img_arr = np.array(img) / 255.0
            # Add a batch dimension
            preprocessed_input = np.expand_dims(img_arr, axis=0)
            # Add a channel dimension
            preprocessed_input = np.expand_dims(preprocessed_input, axis=-1)
            # Make the prediction
            predictions = model.predict(preprocessed_input)
        return  predictions[0][0]
    except Exception as e:
        # Handle exceptions, e.g., if the image couldn't be downloaded or processed
        print(f"An error occurred: {str(e)}")
        return None
    

