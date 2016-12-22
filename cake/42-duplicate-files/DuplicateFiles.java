/*
Task: find all potentially duplicate files from some given root.
*/

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import javax.xml.bind.DatatypeConverter;

public class DuplicateFiles {

	private static MessageDigest md = null;

	public static class PotentialDuplicate {
		String originalPath;
		String duplicatePath;

		public PotentialDuplicate(String originalPath, String duplicatePath) {
			this.originalPath  = originalPath;
			this.duplicatePath = duplicatePath;
		}

		@Override
		public String toString() {
			return "(original: " + originalPath + ", duplicate: " + duplicatePath + ")";
		}
	}

	public static void findDuplicates(File root, HashMap<String, File> paths, ArrayList<PotentialDuplicate> dups) {
		Stack<File> openList = new Stack<>();
		HashSet<String> closedList = new HashSet<>();

		openList.add(root);

		while (!openList.isEmpty()) {
			File dir = openList.pop();
			closedList.add(dir.getAbsolutePath());

			File[] files = dir.listFiles();
			if (files == null) continue;

			for (File f : files) {
				if (f.getName().equals(".") || f.getName().equals("..")) continue;

				if (f.isDirectory()) {
					if (!closedList.contains(f.getAbsolutePath())) {
						openList.push(f);
					}
				} else {
					evalFile(f, paths, dups);
				}
			}
		}
	}

	public static void evalFile(File file, HashMap<String, File> paths, ArrayList<PotentialDuplicate> dups) {
		String fingerprint = getFileFingerprint(file);
		if (fingerprint == null) return;

		if (paths.containsKey(fingerprint)) {
			File found = paths.get(fingerprint);
			if (found.lastModified() < file.lastModified()) {
				// we already saw the original
				dups.add(new PotentialDuplicate(found.getAbsolutePath(), file.getAbsolutePath()));
				paths.put(fingerprint, found);
			} else {
				// we already saw the duplicate
				dups.add(new PotentialDuplicate(file.getAbsolutePath(), found.getAbsolutePath()));
				paths.put(fingerprint, file);
			}
		} else {
			paths.put(fingerprint, file);
		}
	}

	public static String getFileFingerprint(File file) {
		if (md == null) {
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException("MD5 failed");
			}
		}

		int blockSize = 4096;
		int blocksToTake = 3;

		try {
			InputStream inputStream = new FileInputStream(file);
			DigestInputStream digestInputStream = new DigestInputStream(inputStream, md);

			long totalFileBytes = file.length();

			if (totalFileBytes < blockSize * blocksToTake) {
				// read the whole file if it's too short
				byte[] bytes = new byte[(int) totalFileBytes];
				digestInputStream.read(bytes);
			} else {
				// otherwise, take evenly spaced chunks
				byte[] bytes = new byte[(int) blockSize * blocksToTake];
				long bytesToSkip = totalFileBytes - (blockSize * blocksToTake) / (blocksToTake - 1);

				for (int i = 0; i < blocksToTake; ++i) {
					digestInputStream.read(bytes, i * blockSize, blockSize);
					digestInputStream.skip(bytesToSkip);
				}
			}

			byte[] hashBytes = md.digest();
			md.reset();
			return DatatypeConverter.printHexBinary(hashBytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String... args) {
		if (args.length != 1) {
			System.err.println("Usage: java DuplicateFiles <root path>");
			return;
		}

		String rootPath = args[0].trim();
		File root = new File(rootPath);
		if (!root.exists()) {
			System.err.println(rootPath + " does not exist!");
			return;
		}
		if (!root.isDirectory()) {
			System.err.println(rootPath + " is not a directory!");
			return;
		}

 		ArrayList<PotentialDuplicate> dups = new ArrayList<>();
		HashMap<String, File> paths = new HashMap<>();
		findDuplicates(root, paths, dups);

		for (PotentialDuplicate d : dups) {
			System.out.println(d);
		}
	}

}