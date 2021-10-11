from flask import Flask, request, render_template
from werkzeug.utils import secure_filename
import os
import io
import pandas as pd
import matplotlib.pyplot as plt
import base64

app = Flask(__name__)

app.config["data"] = "./info"


@app.route('/')
def main():
    return render_template('home.html')


@app.route('/columnas', methods=['POST'])
def columnas():
    if request.method == 'POST':
        da = request.files['file']
        filename = secure_filename(da.filename)
        da.save(os.path.join(app.config["data"], filename))
        df = pd.read_csv('./info/{}'.format(filename),index_col=["país_Name"])
        columnas = {
            'columnas': df.columns.tolist(),
            'filename': filename
        }

        return render_template('generador.html', columnas=columnas)


@app.route('/grafica', methods=['POST'])
def grafica():
    if request.method == 'POST':
        columna = request.form['columna']
        grafica = request.form['tipo']
        filename = request.form['filename']
        df = pd.read_csv('./info/{}'.format(filename))[columna]

        plt.clf()
        if grafica == 'puntos':
            img = io.BytesIO()
            plt.title("la grafica por: " + columna)
            plt.plot(df.head(86), '--')
            plt.savefig(img, format='png')
            img.seek(0)
            plot_url = base64.b64encode(img.getvalue()).decode()
            return render_template('grafica.html', imagen={'imagen': plot_url})
        elif grafica == 'lineas':
            print('lineas')
            img = io.BytesIO()
            plt.title("la grafica por: " + columna)
            plt.plot(df.head())
            plt.savefig(img, format='png')
            img.seek(0)
            plot_url = base64.b64encode(img.getvalue()).decode()
            return render_template('grafica.html', imagen={'imagen': plot_url})
        elif grafica == 'barra':
            img = io.BytesIO()
            plt.title("la grafica por: " + columna)
            #datos = df.head().tolist()
            datos=df[["país_Code",columna]].plot(kind='bar')

            plt.savefig(img, format='png')
            plt.savefig(img, format='png')
            img.seek(0)
            plot_url = base64.b64encode(img.getvalue()).decode()
            return render_template('grafica.html', imagen={'imagen': plot_url})



if __name__ == "__main__":
    app.run(port=8080, debug=True)
