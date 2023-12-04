from PIL import Image


img = Image.open('turt.png')
black_and_white = img.convert('L')
black_and_white.save('bw_turt.png')
