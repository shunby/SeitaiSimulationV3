package seitaiv3.main.stuff;
/**
 * 2Dベクトルを扱うクラスです。
 *
 * 内積や外積、ベクトルの加算乗除などほぼ全ての演算ができます。
 * http://javaappletgame.blog34.fc2.com/blog-entry-53.html
 *
 * @author saharan
 *
 */
public class Vector implements Cloneable {

	/** ベクトルの値です。 */
	public float x, y;

	/**
	 * それぞれの初期の値を指定して作成します。
	 *
	 * @param x
	 *            初期x値
	 * @param y
	 *            初期y値
	 */
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * それぞれの初期の値を0に指定して作成します。
	 */
	public Vector() {
		this(0, 0);
	}

	/**
	 * 既存ベクトルをコピーして作成します。
	 */
	public Vector(Vector v) {
		this(v.x, v.y);
	}

	/**
	 * 値をセットします。
	 *
	 * @param x
	 *            x値
	 * @param y
	 *            y値
	 */
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 値を0にリセットします。
	 */
	public void zero() {
		set(0, 0);
	}

	/**
	 * ベクトルを加算します。
	 *
	 * @param v
	 *            加算するベクトル
	 * @return 加算されたベクトル
	 */
	public Vector add(Vector v) {
		x += v.x;
		y += v.y;
		return this;
	}

	/**
	 * ベクトルを減算します。
	 *
	 * @param v
	 *            減算するベクトル
	 * @return 減算されたベクトル
	 */
	public Vector sub(Vector v) {
		x -= v.x;
		y -= v.y;
		return this;
	}

	/**
	 * ベクトルを乗算します。
	 *
	 * @param v
	 *            乗算するベクトル
	 * @return 乗算されたベクトル
	 */
	public Vector mul(float f) {
		x *= f;
		y *= f;
		return this;
	}

	/**
	 * ベクトルを除算します。
	 *
	 * @param v
	 *            除算するベクトル
	 * @return 除算されたベクトル
	 */
	public Vector div(float f) {
		if (f == 0)
			return this;
		x /= f;
		y /= f;
		return this;
	}

	/**
	 * ベクトルを加算した結果を返します。
	 *
	 * @param v
	 *            加算するベクトル
	 * @return 加算されたベクトル
	 */
	public Vector getAdd(Vector v) {
		return new Vector(x + v.x, y + v.y);
	}

	/**
	 * ベクトルを減算した結果を返します。
	 *
	 * @param v
	 *            減算するベクトル
	 * @return 減算されたベクトル
	 */
	public Vector getSub(Vector v) {
		return new Vector(x - v.x, y - v.y);
	}

	/**
	 * ベクトルを乗算した結果を返します。
	 *
	 * @param v
	 *            乗算するベクトル
	 * @return 乗算されたベクトル
	 */
	public Vector getMul(float f) {
		return new Vector(x * f, y * f);
	}

	/**
	 * ベクトルを除算した結果を返します。
	 *
	 * @param v
	 *            除算するベクトル
	 * @return 除算されたベクトル
	 */
	public Vector getDiv(float f) {
		if (f == 0)
			return this;
		return new Vector(x / f, y / f);
	}

	/**
	 * ベクトルの長さを返します。
	 *
	 * @return このベクトルの長さ
	 */
	public float getLength() {
		return (float) Math.sqrt(x * x + y * y);
	}

	/**
	 * ベクトルを正規化します。
	 *
	 * 正規化されたベクトルは getLength() == 1 という条件を見たします。
	 *
	 * ただし非常に長さが短いときや、長さが0のときは正しく正規化されません。
	 */
	public void normalize() {
		float length = getLength();
		if (length < 0.0001f)
			length = 1.0f;
		x /= length;
		y /= length;
	}

	/**
	 * 正規化されたベクトルを返します。
	 *
	 * @return 正規化されたベクトル
	 * @see #normalize()
	 */
	public Vector getNormalize() {
		float length = getLength();
		if (length < 0.0001f)
			length = 1.0f;
		return new Vector(x / length, y / length);
	}

	/**
	 * ベクトルを反転させます。
	 */
	public void reverse() {
		x *= -1;
		y *= -1;
	}

	/**
	 * 反転したベクトルが返されます。
	 *
	 * @return 反転されたベクトル
	 */
	public Vector getReverse() {
		return new Vector(-x, -y);
	}

	/**
	 * ベクトルをラジアン角で回転させます。
	 *
	 * 度数からラジアンへの変更は Mathf.toRadians が使えます。
	 *
	 * @param radian
	 *            ラジアン単位の角度
	 * @see Mathf#toRadians(float degree)
	 */
	public void rotate(float radian) {
		float x2 = (float) (-Math.sin(radian) * y + Math.cos(radian) * x);
		float y2 = (float) (Math.cos(radian) * y + Math.sin(radian) * x);
		x = x2;
		y = y2;
	}

	/**
	 * ベクトルをラジアン角で回転させた結果を返します。
	 *
	 * 度数からラジアンへの変更は Mathf.toRadians が使えます。
	 *
	 * @param radian
	 *            ラジアン単位の角度
	 * @return 回転後のベクトル
	 * @see Mathf#toRadians(float degree)
	 */
	public Vector getRotate(float radian) {
		return new Vector((float) (-Math.sin(radian) * y + Math.cos(radian) * x),
				(float) (Math.cos(radian) * y + Math.sin(radian) * x));
	}

	/**
	 * @see Object#clone()
	 */
	public Vector clone() {
		return new Vector(x, y);
	}

	/**
	 * 二つのベクトルの外積を返します。
	 *
	 * 外積は二つのベクトルによって作られる平行四辺形の面積と等しくなります。
	 *
	 * @param v
	 *            一つ目のベクトル
	 * @param v2
	 *            二つ目のベクトル
	 * @return 外積
	 */
	public static float cross(Vector v, Vector v2) {
		return v.x * v2.y - v.y * v2.x;
	}

	/**
	 * ベクトルの一つが3Dだと仮定したときに、そのz値を指定して外積のベクトルを返します。
	 *
	 * @param z
	 *            z値
	 * @param v
	 *            ベクトル
	 * @return 外積
	 */
	public static Vector cross(float z, Vector v) {
		return new Vector(-z * v.y, z * v.x);
	}

	/**
	 * ベクトルの一つが3Dだと仮定したときに、そのz値を指定して外積のベクトルを返します。
	 *
	 * @param v
	 *            ベクトル
	 * @param z
	 *            z値
	 * @return 外積
	 */
	public static Vector cross(Vector v, float z) {
		return new Vector(-z * v.y, z * v.x);
	}

	/**
	 * 二つのベクトルの内積を返します。
	 *
	 * @param v
	 *            一つ目のベクトル
	 * @param v2
	 *            二つ目のベクトル
	 * @return 内積
	 */
	public static float dot(Vector v, Vector v2) {
		return v.x * v2.x + v.y * v2.y;
	}

	/**
	 * 長さの二乗を返します。
	 *
	 * @return 長さの二乗
	 */
	public float getLengthSquare() {
		return x * x + y * y;
	}

	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return "X=" + x + ", Y=" + y;
	}
}