from PIL import Image
import matplotlib.image as mpimg
import numpy as np
import matplotlib.pyplot as plt


def result_drawer(orig, comp, K):
    fig, axs = plt.subplots(1, 2)
    axs[0].axis('off')
    axs[1].axis('off')

    axs[0].imshow(orig).set_cmap('grey')
    axs[1].imshow(comp).set_cmap('grey')

    axs[0].set_title('Оригинал')
    axs[1].set_title('После сжатия, k=' + str(K))
    plt.show()


img = Image.open('red_eye.png')
black_and_white = img.convert('L')
black_and_white.save('bw_red.png')

matrix_image = mpimg.imread('bw_red.png')

U, s, W = np.linalg.svd(matrix_image)
V = W.T
D = np.zeros_like(matrix_image, dtype=float)
s = np.diag(s)

k = [1, 5, 10, 20, 50, 100, 150, 250, 1000]

for i in k:
    compress = U[:, :i] @ s[0:i, :i] @ W[:i, :]
    result_drawer(matrix_image, compress, i)



#mpimg.imsave('after_svd.png', np.dot(np.dot(U, D), V.T))
