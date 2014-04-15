public class MalitiaActus { //malicious actions
	public void admodumEnim() { // Findbugs doesnt approve of these usages, very malicious indeed
		new Byte("19");
		System.exit(0);
	}
}
